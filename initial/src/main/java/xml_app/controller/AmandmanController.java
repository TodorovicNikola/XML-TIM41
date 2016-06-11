package xml_app.controller;

import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.*;
import xml_app.database.DatabaseHelper;
import xml_app.model.Akt;
import xml_app.model.Amandman;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by Vuletic on 25.5.2016.
 */

@RestController
@RequestMapping("/api/amandmani")
public class AmandmanController {

    @RequestMapping(value = "/dodaj",method = RequestMethod.POST)
    public Amandman trial(@RequestBody String telo) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Amandman.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(telo);

        try{
            Amandman a = (Amandman) unmarshaller.unmarshal(reader);
            DatabaseHelper db = new DatabaseHelper();

            db.writeAmandman(a);

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

}
