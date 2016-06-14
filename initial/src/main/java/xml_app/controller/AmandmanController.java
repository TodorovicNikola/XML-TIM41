package xml_app.controller;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xalan.processor.TransformerFactoryImpl;
import org.apache.xmlgraphics.util.MimeConstants;
import org.springframework.util.xml.SimpleNamespaceContext;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import xml_app.database.DatabaseHelper;
import xml_app.model.*;
import xml_app.model.DTOs.AmandmanString;
import xml_app.model.DTOs.BuildAmandmanDTO;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.*;
import java.io.*;
import java.util.*;

/**
 * Created by Vuletic on 25.5.2016.
 */

@RestController
@RequestMapping("/api/amandmani")
public class AmandmanController {

    @RequestMapping(value="/svi", method = RequestMethod.GET)
    public Collection<Amandman> amandmani(){
        DatabaseHelper db = new DatabaseHelper();

        List<Amandman> amandmani = db.getAmandmani();
        db.release();
        return amandmani;
    }

    @RequestMapping(value = "/dodaj",method = RequestMethod.POST)
    public Amandman dodaj(@RequestBody BuildAmandmanDTO dto) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Amandman.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


        String telo = dto.getAmandman();
        String uuAmId = UUID.randomUUID().toString();

        telo = telo.replace("xml:space='preserve'", "");
        telo = telo.replace("<Amandman","<Amandman Id='" + uuAmId + "' IdAkta='" + dto.getAktId() + "'");


        StringReader reader = new StringReader(telo);

        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(reader));
            doc.getDocumentElement().normalize();

            fillInIds(doc.getDocumentElement(), "/");

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File("XSDs/Amandman.xsd"));
            Schema schema = factory.newSchema(schemaFile);

            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(doc));

            Amandman a = (Amandman) unmarshaller.unmarshal(doc);
            GregorianCalendar date = new GregorianCalendar();
            a.setDatumIVremePodnosenja( DatatypeFactory.newInstance().newXMLGregorianCalendar(date));

            DatabaseHelper db = new DatabaseHelper();
            db.writeAmandman(a);
            db.release();

            return a;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


    @RequestMapping(value = "/dogradi",method = RequestMethod.POST)
    public AmandmanString dogradi(@RequestBody BuildAmandmanDTO dto) throws JAXBException {

        AmandmanString retDto = new AmandmanString();
        retDto.setAm("");

        JAXBContext jaxbContextAm = JAXBContext.newInstance(Amandman.class);
        Unmarshaller unmarshaller = jaxbContextAm.createUnmarshaller();

        StringReader reader = new StringReader(dto.getAmandman());
        Amandman a = (Amandman) unmarshaller.unmarshal(reader);



        if(dto.getAction().equals("Izmeni")) {

            if(!dto.getReference().contains("Clan")){
                return retDto;
            }


            Node node = getNode(dto);

            if(node == null){
                return retDto;
            }

            List<ElementAmandmana> lea = a.getElementAmandmana();
            ElementAmandmana ea = new ElementAmandmana();

            JAXBContext jaxbContextCl = JAXBContext.newInstance(Clan.class);
            Unmarshaller u = jaxbContextCl.createUnmarshaller();
            Clan clan = (Clan) u.unmarshal(node);

            ea.setAkcija(dto.getAction());
            ea.setReferencira(dto.getReference());
            ea.setClan(clan);
            lea.add(ea);


            StringWriter sw = new StringWriter();
            Marshaller m = jaxbContextAm.createMarshaller();
            m.marshal(a,sw);


            retDto.setAm(sw.toString());

            return retDto;

        }



        if(dto.getAction().equals("Dodaj")) {


            if(dto.getReference().contains("Clan")) {
                return  retDto;
            }


            Node node = getNode(dto);
            if(node == null){
                return retDto;
            }

            try{

                if(!dto.getReference().contains("Pododeljak")) {

                    JAXBContext jaxbContextPod = JAXBContext.newInstance(Odeljak.class);
                    Unmarshaller u = jaxbContextPod.createUnmarshaller();
                    Odeljak o = (Odeljak) u.unmarshal(node);

                    if(!o.getPododeljak().isEmpty()){
                        return retDto;
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }



            List<ElementAmandmana> lea = a.getElementAmandmana();
            ElementAmandmana ea = new ElementAmandmana();

            ea.setReferencira(dto.getReference());
            ea.setAkcija(dto.getAction());

            Clan cl =  new Clan();
            cl.setId(" ");

            ea.setClan(cl);
            lea.add(ea);

            StringWriter sw = new StringWriter();
            Marshaller m = jaxbContextAm.createMarshaller();
            m.marshal(a,sw);

            AmandmanString as = new AmandmanString();
            as.setAm(sw.toString());

            return as;
        }



        if(dto.getAction().equals("Obrisi")) {

            if(!dto.getReference().contains("Clan")){
                return retDto;
            }


            Node node = getNode(dto);

            if(node == null){
                return retDto;
            }


            List<ElementAmandmana> lea = a.getElementAmandmana();
            ElementAmandmana ea = new ElementAmandmana();

            ea.setReferencira(dto.getReference());
            ea.setAkcija(dto.getAction());

            lea.add(ea);

            StringWriter sw = new StringWriter();
            Marshaller m = jaxbContextAm.createMarshaller();
            m.marshal(a,sw);

            AmandmanString as = new AmandmanString();
            as.setAm(sw.toString());

            return as;
        }

        return null;

    }


    @Produces (MediaType.TEXT_XML)
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public void konkretanAmandman(@PathVariable String id, HttpServletResponse resp){
        DatabaseHelper db = new DatabaseHelper();

        Amandman a = db.findAmandmanById(id);
        db.release();

        try{
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xslt = new StreamSource("XSDs/amandman.xsl");

            Transformer transformer = tf.newTransformer(xslt);

            JAXBContext jc = JAXBContext.newInstance(Amandman.class);
            JAXBSource source = new JAXBSource(jc, a);

            StreamResult result = new StreamResult(resp.getOutputStream());


            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @RequestMapping(value = "/{amandmanId}/pdf",method = RequestMethod.GET)
    public void konkretanAmandmanPdf(@PathVariable String amandmanId, HttpServletResponse resp) throws IOException {
        //trebalo bi ovo wrapovati u neku metodu, ali za sada neka ostane tako
        DatabaseHelper db = new DatabaseHelper();
        Amandman a = db.findAmandmanById(amandmanId);
        JAXBSource source = null;
        FopFactory fopFactory = null;
        TransformerFactory transformerFactory;
        db.release();
        try {
            JAXBContext jc = JAXBContext.newInstance(Amandman.class);
            source = new JAXBSource(jc, a);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            fopFactory = FopFactory.newInstance(new File("XSDs/fop.xconf"));
            //konf fajl za prikaz, za sada je to onaj sa vezbi.moze se customizovati malo
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        transformerFactory = new TransformerFactoryImpl();
        File xsltFile = new File("XSDs/Amandman_xsl_fo.xsl");
        StreamSource transformSource = new StreamSource(xsltFile);


        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();


        Transformer xslFoTransformer = null;
        try {
            xslFoTransformer = transformerFactory.newTransformer(transformSource);
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

            Result res = new SAXResult(fop.getDefaultHandler());

            xslFoTransformer.transform(source, res);
            // u sax result storuje
            //u outStream su bajti potrebni za pustanje kroz izlazni bafer
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FOPException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println(a.getDatumIVremePodnosenja());
        resp.getOutputStream().write(outStream.toByteArray());
        System.out.println("Outputovan pdf");
    }



    private void fillInIds(Node node, String parentsId){

        Hashtable<String, Integer> namesCount = new Hashtable<String, Integer>();

        if(node.getNodeType() != Node.ELEMENT_NODE)
            return;

        if(!node.hasChildNodes())
            return;

        NodeList childNodes = node.getChildNodes();
        for (int i=0; i < childNodes.getLength(); i++) {
            Node subnode = childNodes.item(i);
            String nameKey =  subnode.getNodeName();

            if (subnode.getNodeType() == Node.ELEMENT_NODE) {

                Integer count;
                Element elNode = (Element) subnode;

                if(namesCount.containsKey(nameKey)){
                    count = namesCount.get(nameKey);
                    namesCount.put(nameKey,++count);
                }else{
                    count = 1;
                    namesCount.put(nameKey, count);
                }

                if(elNode.getAttributeNode("Id") == null) {
                    fillInIds(subnode, "/");

                }else{
                    elNode.getAttributeNode("Id").setValue( parentsId + "/" + nameKey + count.toString());
                    fillInIds(subnode,  parentsId + "/" + nameKey + count.toString());
                }

            }
        }

    }

    private Node getNode(BuildAmandmanDTO dto){

        DatabaseHelper dbh = new DatabaseHelper();
        Akt akt = dbh.findAktById(dto.getAktId());
        dbh.release();

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            JAXBContext jaxbContextAk = JAXBContext.newInstance(Akt.class);
            Marshaller marshaller = jaxbContextAk.createMarshaller();
            marshaller.marshal(akt, doc);

            HashMap<String, String> prefMap = new HashMap<>();
            prefMap.put("ns2", "http://www.xmlProjekat.com/akt");


            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            xPath.setNamespaceContext(new NameSpaceContext(prefMap));


            if(dto.getReference().contains("Clan")) {
                XPathExpression xPathExpression = xPath.compile("//ns2:Clan[@Id = '" + dto.getReference() + "']");
                Node node = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
                return node;
            }

            if(dto.getReference().contains("Pododeljak")) {
                XPathExpression xPathExpression = xPath.compile("//ns2:Pododeljak[@Id = '" + dto.getReference() + "']");
                Node node = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
                return node;
            }

            if(dto.getReference().contains("Odeljak")) {
                XPathExpression xPathExpression = xPath.compile("//ns2:Odeljak[@Id = '" + dto.getReference() + "']");
                Node node = (Node) xPathExpression.evaluate(doc, XPathConstants.NODE);
                return node;
            }

        }catch(Exception e){

            e.printStackTrace();
        }

        return null;
    }
}
