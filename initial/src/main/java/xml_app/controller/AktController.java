package xml_app.controller;

import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import xml_app.database.DatabaseHelper;
import xml_app.model.Akt;

import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Created by Vuletic on 25.5.2016.
 */

@RestController
@RequestMapping("/api/akti")
public class AktController {


    @RequestMapping(value="/usvojeni", method = RequestMethod.GET)
    public Collection<Akt> usvojeniAkti(){
        DatabaseHelper db = new DatabaseHelper();

        List<Akt> akti = db.getUsvojeniAkti();
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

 @RequestMapping(value="/u-nacelu", method = RequestMethod.GET)
    public Collection<Akt> aktiUsvojeniUNacelu(){
        DatabaseHelper db = new DatabaseHelper();

        List<Akt> akti = db.getAktiUsvojeniUNacelu();
        db.release();
        return akti;
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

        resp.getOutputStream().write(outStream.toByteArray());
        System.out.println("Outputovan pdf");
    }


    @RequestMapping(value = "/dodaj",method = RequestMethod.POST)
    public Akt trial(@RequestBody String telo) throws JAXBException {

        String uuid = UUID.randomUUID().toString();

        telo = telo.replace("xml:space='preserve'", "");
        telo = telo.replace("<Akt","<Akt Id='" + uuid + "' Status='U proceduri");
        telo = telo.replace("<Deo","<Deo Id='' ");
        telo = telo.replace("<Glava","<Glava Id='' ");
        telo = telo.replace("<Odeljak","<Odeljak Id='' ");
        telo = telo.replace("<Pododeljak","<Pododeljak Id='' ");
        telo = telo.replace("<Stav","<Stav Id='' ");
        telo = telo.replace("<Tacka","<Tacka Id='' ");
        telo = telo.replace("<Podtacka","<Podtacka Id='' ");
        telo = telo.replace("<Clan","<Clan Id='' ");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(new InputSource(new StringReader(telo)));
            doc.getDocumentElement().normalize();

            fillInIds(doc.getDocumentElement(), doc);

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

            DatabaseHelper db = new DatabaseHelper();
            db.writeAkt(a);


            System.out.print(" Ovo jeee uuid: " + uuid);
            return a;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;


    }

    private void fillInIds(Node node, Document doc){

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
                    continue;
                }else{
                    elNode.getAttributeNode("Id").setValue( nameKey + count.toString());

                }
                fillInIds(subnode, doc);
            }
        }

    }


}
