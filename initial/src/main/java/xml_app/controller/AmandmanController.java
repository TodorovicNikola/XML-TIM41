package xml_app.controller;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xalan.processor.TransformerFactoryImpl;
import org.apache.xmlgraphics.util.MimeConstants;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import xml_app.database.DatabaseHelper;
import xml_app.model.Amandman;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

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
    public Amandman trial(@RequestBody String telo) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Amandman.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        telo = telo.replace("xml:space='preserve'", "");

        StringReader reader = new StringReader(telo);

        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(reader));
            doc.getDocumentElement().normalize();

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File("XSDs/Amandman.xsd"));
            Schema schema = factory.newSchema(schemaFile);

            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(doc));

            Amandman a = (Amandman) unmarshaller.unmarshal(reader);
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

}
