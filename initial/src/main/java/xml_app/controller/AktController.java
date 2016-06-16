package xml_app.controller;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import xml_app.database.DatabaseHelper;
import xml_app.model.Akt;
import xml_app.model.DTOs.StringDTO;
import xml_app.model.utils.PretragaKriterijum;
import xml_app.model.utils.SearchMetadata;

import javax.servlet.http.HttpServletResponse;
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
import java.io.*;
import java.util.*;

/**
 * Created by Vuletic on 25.5.2016.
 */

@RestController
@RequestMapping("/api/akti")
public class AktController {

    private Hashtable<String, Integer> absoluteNamesCount = new Hashtable<>();

    @RequestMapping(value="/usvojeni", method = RequestMethod.GET)
    public Collection<Akt> usvojeniAkti(){
        DatabaseHelper db = new DatabaseHelper();

        List<Akt> akti = db.getUsvojeniAkti();
        db.release();
        return akti;
    }

    @RequestMapping(value="/usvojeniKorisnika", method = RequestMethod.POST)
    public Collection<Akt> usvojeniAktiKorisnika(@RequestBody String userId){
        DatabaseHelper db = new DatabaseHelper();

        List<Akt> akti = db.getUsvojeniAktiKorisnika(userId);
        db.release();
        return akti;
    }

    @RequestMapping(value="/u-proceduri", method = RequestMethod.GET)
    public Collection<Akt> aktiUProceduri(){
        DatabaseHelper db = new DatabaseHelper();

        List<Akt> akti = db.getAktiUProceduri();
        db.release();
        return akti;
    }

    @RequestMapping(value="/uProceduriKorisnika", method = RequestMethod.POST)
    public Collection<Akt> aktiUProceduriKorisnika(@RequestBody String userId){
        DatabaseHelper db = new DatabaseHelper();

        List<Akt> akti = db.getAktiUProceduriKorisnika(userId);
        db.release();
        return akti;
    }

    @RequestMapping(value="/u-nacelu", method = RequestMethod.GET)
    public Collection<Akt> aktiUsvojeniUNacelu(){
        DatabaseHelper db = new DatabaseHelper();

        List<Akt> akti = db.getAktiUsvojeniUNacelu();
        db.release();
        return akti;
    }

    @RequestMapping(value="/uNaceluKorisnika", method = RequestMethod.POST)
    public Collection<Akt> aktiUsvojeniUNaceluKorisnika(@RequestBody String userId){
        DatabaseHelper db = new DatabaseHelper();

        List<Akt> akti = db.getAktiUsvojeniUNaceluKorisnika(userId);
        db.release();
        return akti;
    }

    @RequestMapping(value="/obrisi", method = RequestMethod.POST)
    public String obrisi(@RequestBody String id){
        DatabaseHelper db = new DatabaseHelper();

        db.deleteAkt(id);

        db.release();

        return "{ \"data\": \"Akt povučen!\" }";
    }

    @RequestMapping(value = "/{aktId}",method = RequestMethod.GET)
    public void konkretanAkt(@PathVariable String aktId, HttpServletResponse resp){
        DatabaseHelper db = new DatabaseHelper();

        Akt a = db.findAktById(aktId);

        db.release();

        try{
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xslt = new StreamSource("XSDs/Akt.xsl");

            Transformer transformer = tf.newTransformer(xslt);

            JAXBContext jc = JAXBContext.newInstance(Akt.class);
            JAXBSource source = new JAXBSource(jc, a);

            StreamResult result = new StreamResult(resp.getOutputStream());

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/zaAmandman/{aktId}",method = RequestMethod.GET)
    public void konkretanAktZaAmandman(@PathVariable String aktId, HttpServletResponse resp){
        DatabaseHelper db = new DatabaseHelper();

        Akt a = db.findAktById(aktId);

        db.release();

        try{

            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xslt = new StreamSource("XSDs/AktZaAmandmane.xsl");

            Transformer transformer = tf.newTransformer(xslt);

            JAXBContext jc = JAXBContext.newInstance(Akt.class);
            JAXBSource source = new JAXBSource(jc, a);

            StreamResult result = new StreamResult(resp.getOutputStream());

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/sadrzaj", method = RequestMethod.POST)
    public Collection<Akt> aktiPretraga(@RequestBody PretragaKriterijum kriterijum){
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("evo ga " + kriterijum.getKriterijum());
        String pretraga=kriterijum.getKriterijum();
        DatabaseHelper db = new DatabaseHelper();
        List<Akt> akti = db.getAktiByCriteria(pretraga,kriterijum.getStatus());
        db.release();

        return akti;

    }

    @RequestMapping(value="/pretraga", method = RequestMethod.POST)
    public Collection<Akt> aktiNaprednaPretraga(@RequestBody SearchMetadata metadata){
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("evo ga " + metadata.getVremeDonosenjaDo());
        String podnosilac = metadata.getPodnosilac();
        String tip = metadata.getTip();
        String datumOd = metadata.getVremeDonosenjaOd();
        String datumDo = metadata.getVremeDonosenjaDo();
        String glasnik = metadata.getGlasnik();
        String status = metadata.getStatusAkta();
        DatabaseHelper db = new DatabaseHelper();
        List<Akt> akti = db.getAktiByMetaData(podnosilac,tip,datumOd,datumDo,glasnik,status);
        db.release();

        return akti;

    }



    @RequestMapping(value = "/{aktId}/pdf",method = RequestMethod.GET)
    public void konkretanAktPdf(@PathVariable String aktId, HttpServletResponse resp) throws IOException {
        //trebalo bi ovo wrapovati u neku metodu, ali za sada neka ostane tako
        DatabaseHelper db = new DatabaseHelper();
        Akt a = db.findAktById(aktId);
        JAXBSource source = null;
        FopFactory fopFactory = null;
        TransformerFactory transformerFactory;
        db.release();
        try {
            JAXBContext jc = JAXBContext.newInstance(Akt.class);
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
        File xsltFile = new File("XSDs/Akt_xsl_fo.xsl");
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

        resp.setContentType("application/pdf; charset=UTF-8");
        resp.setCharacterEncoding("utf-8");


        resp.getOutputStream().write(outStream.toByteArray());
        System.out.println("Outputovan pdf");
    }


    @RequestMapping(value = "/dodaj1",method = RequestMethod.POST)
    public StringDTO dodajPrvaFaza(@RequestBody String telo) throws JAXBException {

        String uuid = UUID.randomUUID().toString();

        telo = telo.replace("xml:space='preserve'", "");
        telo = telo.replace("<Akt","<Akt Id='" + uuid + "' Status='U proceduri'");
        telo = telo.replace("<Deo","<Deo Id='' RedniBroj='1' ");
        telo = telo.replace("<Glava","<Glava Id='' RedniBroj='1' ");
        telo = telo.replace("<Odeljak","<Odeljak Id='' RedniBroj='1' ");
        telo = telo.replace("<Pododeljak","<Pododeljak Id='' RedniBroj='1' ");
        telo = telo.replace("<Stav","<Stav Id='' ");
        telo = telo.replace("<Tacka","<Tacka Id='' ");
        telo = telo.replace("<Podtacka","<Podtacka Id='' ");
        telo = telo.replace("<Clan","<Clan Id='' RedniBroj='1' ");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(new InputSource(new StringReader(telo)));
            doc.getDocumentElement().normalize();

            fillInIds(doc.getDocumentElement(), "/");
            absoluteNamesCount.clear();
            //TODO: Aleksa, ovde negde dodela za RedniBroj

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File("XSDs/Akt.xsd"));
            Schema schema = factory.newSchema(schemaFile);

            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(doc));


            JAXBContext jaxbContext = JAXBContext.newInstance(Akt.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Akt a = (Akt) unmarshaller.unmarshal(doc);
            a.setTip("Zakon");

            GregorianCalendar date = new GregorianCalendar();
            a.setDatumPodnosenja( DatatypeFactory.newInstance().newXMLGregorianCalendar(date));

            System.out.print(" Ovo jeee uuid: " + uuid);


            StringWriter sw = new StringWriter();
            Marshaller m = jaxbContext.createMarshaller();
            m.marshal(a,sw);

            StringDTO retDto = new StringDTO();
            retDto.setAm(sw.toString());

            return retDto;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        StringDTO retVal = new StringDTO();
        retVal.setAm("Error");

        return retVal;


    }

    @RequestMapping(value = "/dodaj2",method = RequestMethod.POST)
    public StringDTO dodajDrugaFaza(@RequestBody String telo) throws JAXBException {


        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Akt.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Akt a = (Akt) unmarshaller.unmarshal(new StringReader(telo));

            DatabaseHelper db = new DatabaseHelper();
            db.writeAkt(a);

            db.release();

            StringDTO retVal = new StringDTO();
            retVal.setAm("Ok");

            return retVal;
        }catch (Exception e){
            StringDTO retVal = new StringDTO();
            retVal.setAm("");

            return retVal;
        }
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
                Integer countAbsolute;
                Element elNode = (Element) subnode;

                if(namesCount.containsKey(nameKey)){
                    count = namesCount.get(nameKey);
                    namesCount.put(nameKey,++count);
                }else{
                    count = 1;
                    namesCount.put(nameKey, count);
                }
                if (absoluteNamesCount.containsKey(nameKey)) {
                    countAbsolute = absoluteNamesCount.get(nameKey);
                    absoluteNamesCount.put(nameKey, ++countAbsolute);
                } else {
                    countAbsolute = 1;
                    absoluteNamesCount.put(nameKey, countAbsolute);
                }

                if(elNode.getAttributeNode("Id") == null) {
                    continue;
                }else{
                    elNode.getAttributeNode("Id").setValue( parentsId + "/" + nameKey + count.toString());
                    if (elNode.getAttributeNode("RedniBroj")!=null) {
                        elNode.getAttributeNode("RedniBroj").setValue((countAbsolute.toString()));
                    }

                }
                fillInIds(subnode,  parentsId + "/" + nameKey + count.toString());
            }
        }

    }


}
