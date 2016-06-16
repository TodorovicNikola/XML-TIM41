package xml_app.database;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.*;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.RawQueryByExampleDefinition;
import com.marklogic.client.query.StringQueryDefinition;
import xml_app.model.Akt;
import xml_app.model.Amandman;
import xml_app.model.Korisnik;
import xml_app.utils.DateUtil;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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

        System.out.println("[INFO] Verify the content at: http://" + props.host + ":8000/v1/documents?database=" + props.database + "&uri=" + a.getId());
    }

    public void writeAmandman(Amandman a){

        String collId = "amandmani";
        String docId = "amandmani/" + a.getId() + ".xml";

        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(collId);

        // Write the document to the database
        manager.writeAs(docId, metadata, a);

        System.out.println("[INFO] Verify the content at: http://" + props.host + ":8000/v1/documents?database=" + props.database + "&uri=" + a.getId());
    }

    public void deleteAkt(String id){

        QueryManager queryMgr = client.newQueryManager();

        String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/amandman\">\n" +
                "  <q:query>\n" +
                "      <a:Amandman IdAkta='" + id + "'></a:Amandman>\n" +
                "  </q:query>\n" +
                "</q:qbe>";
        StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
        RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");


        SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Amandman a = manager.readAs(docSum.getUri(), Amandman.class);
            deleteAmandman(a.getId());
        }


        manager.delete("akti/" + id + ".xml");

    }

    public void deleteAmandman(String id){
        manager.delete("amandmani/" + id + ".xml");
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

    public Akt findAktById(String id) {
        String docId = "akti/" + id + ".xml";
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

        String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/akt\">\n" +
                "  <q:query>\n" +
                "      <a:Akt Status='Usvojen'></a:Akt>\n" +
                "  </q:query>\n" +
                "</q:qbe>";
        StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
        RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");

        List<Akt> ret = new ArrayList<>();

        SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Akt a = manager.readAs(docSum.getUri(), Akt.class);
            if(a.getStatus().equals("Usvojen"))
                ret.add(a);
        }

        return ret;

    }

    public List<Akt> getAktiByCriteria(String searchCriteria,String tip){
        QueryManager queryMgr = client.newQueryManager();

        StringQueryDefinition stringQry = queryMgr.newStringDefinition();
        stringQry.setCollections("akti");
        stringQry.setCriteria(searchCriteria);

        List<Akt> ret = new ArrayList<>();

        SearchHandle searchHandle = queryMgr.search(stringQry, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Akt a = manager.readAs(docSum.getUri(), Akt.class);
            if (a.getStatus().equals(tip)) {
                System.out.println(a.getStatus()+"sss");
                ret.add(a);
            }
        }

        System.out.println(ret.size());

        return ret;

    }


       public List<Akt> getAktiUsvojeniUNacelu(){
           QueryManager queryMgr = client.newQueryManager();

           String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/akt\">\n" +
                   "  <q:query>\n" +
                   "      <a:Akt Status='U nacelu'></a:Akt>\n" +
                   "  </q:query>\n" +
                   "</q:qbe>";
           StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
           RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");

           List<Akt> ret = new ArrayList<>();

           SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());

           for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {
               Akt a = manager.readAs(docSum.getUri(), Akt.class);

               if(a.getStatus().equals("U nacelu"))

                   ret.add(a);
           }

           return ret;
       }

    public List<Akt> getAktiUProceduri(){
        QueryManager queryMgr = client.newQueryManager();

        String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/akt\">\n" +
                "  <q:query>\n" +
                "      <a:Akt Status='U proceduri'></a:Akt>\n" +
                "  </q:query>\n" +
                "</q:qbe>";
        StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
        RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");

        List<Akt> ret = new ArrayList<>();

        SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Akt a = manager.readAs(docSum.getUri(), Akt.class);
            if(a.getStatus().equals("U proceduri"))
                ret.add(a);
        }

        return ret;

    }

    public List<Akt> getUsvojeniAktiKorisnika(String userId){
        List<Akt> ret = new ArrayList<>();

        QueryManager queryMgr = client.newQueryManager();

        String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/akt\">\n" +
                "  <q:query>\n" +
                "      <a:Akt userId='" + userId + "' Status='Usvojen'></a:Akt>\n" +
                "  </q:query>\n" +
                "</q:qbe>";
        StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
        RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");


        SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Akt a = manager.readAs(docSum.getUri(), Akt.class);
            ret.add(a);
        }

        return ret;

    }

    public List<Akt> getAktiUProceduriKorisnika(String userId){
        List<Akt> ret = new ArrayList<>();

        QueryManager queryMgr = client.newQueryManager();

        String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/akt\">\n" +
                "  <q:query>\n" +
                "      <a:Akt userId='" + userId + "' Status='U proceduri'></a:Akt>\n" +
                "  </q:query>\n" +
                "</q:qbe>";
        StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
        RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");


        SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Akt a = manager.readAs(docSum.getUri(), Akt.class);
            ret.add(a);
        }

        return ret;
    }

    public List<Akt> getAktiUsvojeniUNaceluKorisnika(String userId){
        List<Akt> ret = new ArrayList<>();

        QueryManager queryMgr = client.newQueryManager();

        String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/akt\">\n" +
                "  <q:query>\n" +
                "      <a:Akt userId='" + userId + "' Status='U nacelu'></a:Akt>\n" +
                "  </q:query>\n" +
                "</q:qbe>";
        StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
        RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");


        SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Akt a = manager.readAs(docSum.getUri(), Akt.class);
            ret.add(a);
        }

        return ret;
    }

    public List<Amandman> getAmandmaniKorisnika(String userId){
        List<Amandman> ret = new ArrayList<>();
        QueryManager queryMgr = client.newQueryManager();

        String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/amandman\">\n" +
                "  <q:query>\n" +
                "      <a:Amandman userId='" + userId + "'></a:Amandman>\n" +
                "  </q:query>\n" +
                "</q:qbe>";
        StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
        RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");


        SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Amandman a = manager.readAs(docSum.getUri(), Amandman.class);
            ret.add(a);
        }

        return ret;
    }

    public List<Akt> getAktiByMetaData(String podnosilac, String tip, String datumOd, String datumDo, String glasnik, String status){
        QueryManager queryMgr = client.newQueryManager();
        String attributes = " Status='" + status + "'";
        String podnosilacXml = "";
        Date d1 = null;
        Date d2 = null;

        if(glasnik != "" && glasnik != null)
            attributes += " SluzbeniGlasnik='" + glasnik + "'";

        if(tip != "" && tip != null)
            attributes += " Tip='" + tip + "'";

        if(podnosilac != "" && podnosilac != null)
            podnosilacXml = "<a:Podnosilac><q:word>" + podnosilac + "</q:word></a:Podnosilac>";

        if(datumOd != null){
            d1 = DateUtil.parseDate(datumOd);
        }

        if(datumDo != null){
            d2 = DateUtil.parseDate(datumDo);
        }

        String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/akt\">\n" +
                "  <q:query>\n" +
                "      <a:Akt" + attributes + ">" + podnosilacXml + "</a:Akt>\n" +
                "  </q:query>\n" +
                "</q:qbe>";
        StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
        RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");

        List<Akt> ret = new ArrayList<>();

        SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Akt a = manager.readAs(docSum.getUri(), Akt.class);
            if(d1 != null && d2 != null) {
                Date aktDate = a.getDatumPodnosenja().toGregorianCalendar().getTime();
                int cmp1 = d1.compareTo(aktDate);
                int cmp2 = d2.compareTo(aktDate);

                if(cmp1 <= 0 && cmp2 >= 0)
                    ret.add(a);

            }else if (d1 != null){
                Date aktDate = a.getDatumPodnosenja().toGregorianCalendar().getTime();
                int cmp = d1.compareTo(aktDate);
                if (cmp <= 0)
                    ret.add(a);
            }else if (d2 != null){
                Date aktDate = a.getDatumPodnosenja().toGregorianCalendar().getTime();
                int cmp = d2.compareTo(aktDate);
                if (cmp >= 0)
                    ret.add(a);
            }else{
                ret.add(a);
            }

        }

        return ret;

    }

    public List<Amandman> getAmandmaniAkta(String IdAkta){
        List<Amandman> ret = new ArrayList<>();
        QueryManager queryMgr = client.newQueryManager();

        String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/amandman\">\n" +
                "  <q:query>\n" +
                "      <a:Amandman IdAkta='" + IdAkta + "'></a:Amandman>\n" +
                "  </q:query>\n" +
                "</q:qbe>";
        StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
        RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");


        SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Amandman a = manager.readAs(docSum.getUri(), Amandman.class);
            ret.add(a);
        }

        return ret;
    }

    public List<Amandman> getAmandmani(){
        QueryManager queryMgr = client.newQueryManager();

        StringQueryDefinition stringQry = queryMgr.newStringDefinition();
        stringQry.setCollections("amandmani");

        List<Amandman> ret = new ArrayList<>();

        SearchHandle searchHandle = queryMgr.search(stringQry, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Amandman a = manager.readAs(docSum.getUri(), Amandman.class);
            ret.add(a);
        }

        return ret;

    }

    public Amandman findAmandmanById(String id) {
        String docId = "amandmani/" + id + ".xml";
        Amandman a;
        try {
            a = manager.readAs(docId, Amandman.class);
        } catch (Exception e) {
            a = null;
        }

        return a;
    }
    ///////////////////////////////----------------------------STATE METHODS------------------------------////////////////////////////////
    public void initState(){
        String collId = "state";
        String docId = "stateVal";

        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(collId);

        // Write the document to the database
        manager.writeAs(docId, metadata, "idle");
    }

    public String getState(){
        String docId = "stateVal";
        String state;

        try {
            state = manager.readAs(docId, String.class);
        } catch (Exception e) {
            state = null;
        }

        return state;
    }

    public void nextState(){
        String docId = "stateVal";
        String collId = "state";
        String state;

        try {
            state = manager.readAs(docId, String.class);
        } catch (Exception e) {
            state = null;
        }

        state = state.trim();

        switch(state){
            case "idle": {
                state = "predlaganjeAkata";

            } break;
            case "predlaganjeAkata": {
                state = "predlaganjeAmandmana";

            } break;
            case "predlaganjeAmandmana": {
                state = "glasanjeUNacelu";

            } break;
            case "glasanjeUNacelu": {
                state = "glasanjeZaAmandmane";

            } break;
            case "glasanjeZaAmandmane": {
                state = "glasanjeUCelosti";

            } break;
            case "glasanjeUCelosti": {
                state = "idle";

            } break;
        }

        DocumentMetadataHandle metadata = new DocumentMetadataHandle();
        metadata.getCollections().add(collId);

        // Write the document to the database
        manager.writeAs(docId, metadata, state);
    }

    public void search(){

       QueryManager queryMgr = client.newQueryManager();


        /*QueryOptionsManager optionsMgr = client.newServerConfigManager().newQueryOptionsManager();
        QueryOptionsBuilder qob = new QueryOptionsBuilder();

        QueryOptionsHandle optsHandle = new QueryOptionsHandle().withConstraints(
                qob.constraint("Status",
                        qob.properties()));

        optionsMgr.writeOptions("akt", optsHandle);

///////////////////////////////////////////////////////////////////////////
        QueryOptionsListHandle listHandle = new QueryOptionsListHandle();

        queryMgr.optionsList(listHandle);

        for (Map.Entry<String,String> optionsSet : listHandle.getValuesMap().entrySet()) {
            System.out.println(optionsSet.getKey() + ": " + optionsSet.getValue());
        }

        StringQueryDefinition stringQry = queryMgr.newStringDefinition();
        stringQry.setCollections("akti");

        stringQry.setOptionsName("akt");
        //stringQry.setCriteria("Status:Usvojen");*/

        /*KeyValueQueryDefinition kvqdef = queryMgr.newKeyValueDefinition();
        kvqdef.put(queryMgr.newElementLocator(new QName("Akt"),new QName("Status")), "Usvojen");*/


        /*String rawXMLQuery = "<q:qbe xmlns:q=\"http://marklogic.com/appservices/querybyexample\" xmlns:a=\"http://www.xmlProjekat.com/akt\">\n" +
                "  <q:query>\n" +
                "      <a:Akt Status='Usvojen'></a:Akt>\n" +
                "  </q:query>\n" +
                "</q:qbe>";
        StringHandle qbeHandle = new StringHandle(rawXMLQuery).withFormat(Format.XML);
        RawQueryByExampleDefinition query = queryMgr.newRawQueryByExampleDefinition(qbeHandle, "akt");


        SearchHandle searchHandle = queryMgr.search(query, new SearchHandle());
        for (MatchDocumentSummary docSum: searchHandle.getMatchResults()) {

            Akt a = manager.readAs(docSum.getUri(), Akt.class);
            System.out.println(a.getNaslov() + a.getStatus());
        }*/

    }


}
