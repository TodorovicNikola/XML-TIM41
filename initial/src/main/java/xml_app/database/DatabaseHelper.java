package xml_app.database;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.io.JAXBHandle;
import xml_app.model.Akt;
import xml_app.model.Amandman;
import xml_app.model.Korisnik;


import javax.xml.bind.JAXBException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class DatabaseHelper {

    private DatabaseClient client;
    private XMLDocumentManager manager;
    private Configuration.ConnectionProperties props;
    public DatabaseHelper(){
        try {
            DatabaseClientFactory.getHandleRegistry().register(
                    JAXBHandle.newFactory(Korisnik.class, Amandman.class, Akt.class)
            );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        props = Configuration.loadProperties();
            client = DatabaseClientFactory.newClient(props.host, props.port, props.database, props.user, props.password, props.authType);
            manager = client.newXMLDocumentManager();
    }

    public void release(){
        client.release();
    }

    public void write(String XMLPath, String docId){
        // Create an input stream handle to hold XML content.
        InputStreamHandle handle = null;
        try {
            handle = new InputStreamHandle(new FileInputStream(XMLPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Write the document to the

        manager.write(docId, handle);

        System.out.println("[INFO] Verify the content at: http://" + props.host + ":8000/v1/documents?database=" + props.database + "&uri=" + docId);
    }

    public void writeKorisnik(Korisnik k){

        String collId = "korisnici";
        String docId = "korisnici/" + k.getKorisnickoIme() + ".xml";

        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(collId);

        // Write the document to the database
        manager.writeAs(docId, metadata, k);

        System.out.println("[INFO] Verify the content at: http://" + props.host + ":8000/v1/documents?database=" + props.database + "&uri=" + Integer.toString(k.getId()));
    }

    public Korisnik findKorisnikById(String username){
        String docId = "korisnici/" + username + ".xml";
        Korisnik k;
        try {
            k = manager.readAs(docId, Korisnik.class);
        }catch (Exception e){
            k = null;
        }

        return k;
    }



}
