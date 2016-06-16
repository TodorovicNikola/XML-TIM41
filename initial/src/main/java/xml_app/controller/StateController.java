package xml_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml_app.database.DatabaseHelper;
import xml_app.model.Akt;
import xml_app.model.Amandman;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

@RestController
@RequestMapping("/api/state")
public class StateController {
    private Hashtable<String, Integer> absoluteNamesCount = new Hashtable<>();

    @RequestMapping(value="/getState", method = RequestMethod.GET)
    public String getState(){
        DatabaseHelper db = new DatabaseHelper();
        String retVal = "{ \"data\": \"" + db.getState() + "\" }";

        db.release();

        return retVal;
    }

    @RequestMapping(value="/nextState", method = RequestMethod.GET)
    public String nextState(){
        DatabaseHelper db = new DatabaseHelper();

        switch (db.getState()){
            case "glasanjeUNacelu": {
                /*if(db.getAktiUProceduri().isEmpty()){
                     db.nextState();

                 }else{
                    return "{ \"data\": \"error\" }";

                 }*/
                db.nextState();
            } break;
            case "glasanjeZaAmandmane": {

                List<Akt> akti=db.getAktiUsvojeniUNacelu();
                for (Akt akt:akti)
                {
                    DocumentBuilderFactory dbFactoryAkt = DocumentBuilderFactory.newInstance();

                    dbFactoryAkt.setNamespaceAware(true);
                    DocumentBuilder dBuilderAkt = null;

                    try {
                        dBuilderAkt = dbFactoryAkt.newDocumentBuilder();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                    Document docAkt = dBuilderAkt.newDocument();
                    JAXBContext jaxbContextAkt = null;
                    try {
                        jaxbContextAkt = JAXBContext.newInstance(Akt.class);
                        Marshaller marshallerAkt = jaxbContextAkt.createMarshaller();
                        marshallerAkt.marshal(akt, docAkt);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                    fillInIds(docAkt.getDocumentElement(),"/");
                    absoluteNamesCount.clear();

                    JAXBContext jc = null;
                    try {
                        jc = JAXBContext.newInstance(Akt.class);
                        Unmarshaller u = jc.createUnmarshaller();

                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        Akt a = (Akt) u.unmarshal(docAkt);
                        db.writeAkt(a);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }

                }
                /*if(db.getAmandmani().isEmpty()){
                       //TODO: Aleksa, ovde negde ponovna dodela za Id i za RedniBroj

                     db.nextState();

                 }else{
                     return "{ \"data\": \"error\" }";

                 }*/

                db.nextState();
            } break;
            case "glasanjeUCelosti": {
                /*if(db.getAktiUsvojeniUNacelu().isEmpty()){
                     db.nextState();
                }else{
                    return "{ \"data\": \"error\" }";

                 }*/
                db.nextState();
            } break;
            default: { db.nextState(); }
            break;
        }

        String retVal = "{ \"data\": \"" + db.getState() + "\" }";

        db.release();

        return retVal;
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
            String nameKey =  subnode.getLocalName();

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
