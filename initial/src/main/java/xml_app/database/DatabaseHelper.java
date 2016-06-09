package xml_app.database;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.*;
import com.marklogic.client.query.*;
import com.marklogic.client.util.EditableNamespaceContext;
import org.w3c.dom.Document;
import xml_app.model.Akt;
import xml_app.model.Amandman;
import xml_app.model.Korisnik;


import javax.xml.bind.JAXBException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


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

    public void write(String XMLPath, String collId, String docId){
        // Create an input stream handle to hold XML content.
        InputStreamHandle handle = null;
        try {
            handle = new InputStreamHandle(new FileInputStream(XMLPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(collId);
        // Write the document to the

        manager.write(docId, metadata, handle);

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

    public void writeAkt(Akt a){

        String collId = "akti";
        String docId = "akti/" + a.getId() + ".xml";

        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(collId);

        // Write the document to the database
        manager.writeAs(docId, metadata, a);

        System.out.println("[INFO] Verify the content at: http://" + props.host + ":8000/v1/documents?database=" + props.database + "&uri=" + Integer.toString(a.getId()));
    }

    public void writeAmandman(Amandman a){

        String collId = "amandmani";
        String docId = "amandmani/" + a.getId() + ".xml";

        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(collId);

        // Write the document to the database
        manager.writeAs(docId, metadata, a);

        System.out.println("[INFO] Verify the content at: http://" + props.host + ":8000/v1/documents?database=" + props.database + "&uri=" + Integer.toString(a.getId()));
    }

    public Korisnik findKorisnikById(String username) {
        String docId = "korisnici/" + username + ".xml";
        Korisnik k;
        try {
            k = manager.readAs(docId, Korisnik.class);
        } catch (Exception e) {
            k = null;
        }

        return k;
    }

    public Akt findAktById(int id) {
        String docId = "akti/" + Integer.toString(id) + ".xml";
        Akt a;
        try {
            a = manager.readAs(docId, Akt.class);
        } catch (Exception e) {
            a = null;
        }

        return a;
    }

    public List<Akt> getUsvojeniAkti(){
        QueryManager queryMgr = client.newQueryManager();

        StringQueryDefinition stringQry = queryMgr.newStringDefinition();
        stringQry.setCollections("akti");

        List<Akt> ret = new ArrayList<>();

        SearchHandle searchHandle = queryMgr.search(stringQry, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Akt a = manager.readAs(docSum.getUri(), Akt.class);
            if(a.getStatus().equals("Usvojen"))
                ret.add(a);
        }

        return ret;

    }

    public List<Akt> getAktiUProceduri(){
        QueryManager queryMgr = client.newQueryManager();

        StringQueryDefinition stringQry = queryMgr.newStringDefinition();
        stringQry.setCollections("akti");

        List<Akt> ret = new ArrayList<>();

        SearchHandle searchHandle = queryMgr.search(stringQry, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Akt a = manager.readAs(docSum.getUri(), Akt.class);
            if(a.getStatus().equals("U proceduri"))
                ret.add(a);
        }

        return ret;

    }

}
