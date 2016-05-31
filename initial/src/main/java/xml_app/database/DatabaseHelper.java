package xml_app.database;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.InputStreamHandle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DatabaseHelper {

    private DatabaseClient client;
    private XMLDocumentManager manager;
    private Configuration.ConnectionProperties props;
    public DatabaseHelper(){

            props = Configuration.loadProperties();
            client = DatabaseClientFactory.newClient(props.host, props.port, props.database, props.user, props.password, props.authType);
            manager = client.newXMLDocumentManager();
    }

    public void write(String XMLPath, String docId){
        // Create an input stream handle to hold XML content.
        InputStreamHandle handle = null;
        try {
            handle = new InputStreamHandle(new FileInputStream(XMLPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Write the document to the database
        manager.write(docId, handle);

        System.out.println("[INFO] Verify the content at: http://" + props.host + ":8000/v1/documents?database=" + props.database + "&uri=" + docId);
        client.release();
    }

}
