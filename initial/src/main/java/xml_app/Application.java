package xml_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xml_app.model.Korisnik;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        /*Korisnik k = new Korisnik("Ime","Prezasdime","email@mail.com","pass","user","064 111 111","Odbornik",111);

        try {

            File file = new File("D:\\file.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Korisnik.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            FileWriter fw = new FileWriter(file);
            fw.write("<?xml version='1.0'?>\n");
            fw.write("<?xml-stylesheet type='text/xsl' href=\"fafa\"?>\n");
            //Do this or else the XML is all one line and not human friendly...
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            jaxbMarshaller.marshal(k, fw);

        } catch (JAXBException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }*/

        SpringApplication.run(Application.class, args);

    }
}
