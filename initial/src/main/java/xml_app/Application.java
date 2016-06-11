package xml_app;

import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryBuilder;
import com.marklogic.client.query.StructuredQueryDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xml_app.database.DatabaseHelper;
import xml_app.model.Korisnik;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

       /* Korisnik k1 = new Korisnik("Jovan","Jovanović","jovan@mail.com","pass","user","064 111 111","Odbornik",3);
        Korisnik k2 = new Korisnik("Petar","Petrović","predsednik@mail.com","a","a","064 111 111","Predsednik",4);

        DatabaseHelper db = new DatabaseHelper();
        db.writeKorisnik(k1);
        db.writeKorisnik(k2);

        db.write("XSDs/Akt.xsd.xml", "akti", "akti/2.xml");
        db.write("XSDs/Akt2.xsd.xml", "akti", "akti/1.xml");
        db.write("XSDs/Akt3.xsd.xml", "akti", "akti/3.xml");
        db.write("XSDs/Akt4.xsd.xml", "akti", "akti/4.xml");
        db.write("XSDs/Amandman.xsd.xml", "amandmani", "amandmani/1.xml");

        db.release();*/


        SpringApplication.run(Application.class, args);
    }
}
