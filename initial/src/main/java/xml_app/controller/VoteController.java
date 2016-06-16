package xml_app.controller;


import org.w3c.dom.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Node;
import xml_app.database.DatabaseHelper;
import xml_app.model.*;
import xml_app.model.DTOs.VoteDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
    private Hashtable<String, Integer> absoluteNamesCount = new Hashtable<>();

    @RequestMapping(value = "/voteUNacelu", method = RequestMethod.POST)
    public String voteUNacelu(@RequestBody VoteDTO votes) {
        DatabaseHelper db = new DatabaseHelper();
        Akt a = db.findAktById(votes.getId());

        if (votes.getGlasoviZa() > votes.getGlasoviProtiv() + votes.getGlasoviUzdrzani()) {
            a.setUNaceluZa(BigInteger.valueOf(votes.getGlasoviZa()));
            a.setUNaceluProtiv(BigInteger.valueOf(votes.getGlasoviProtiv()));
            a.setUNaceluUzdrzano(BigInteger.valueOf(votes.getGlasoviUzdrzani()));

            a.setStatus("U nacelu");

            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(new Date(System.currentTimeMillis()));
            XMLGregorianCalendar xmlGrogerianCalendar = null;
            try {
                xmlGrogerianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }

            a.setDatumGlasanja(xmlGrogerianCalendar);

            db.writeAkt(a);

            db.release();

            return "{ \"data\": \"Akt označen kao 'USVOJEN U NAČELU'!\" }";
        } else {

            db.deleteAkt(votes.getId());

            db.release();

            return "{ \"data\": \"Akt obrisan!\" }";

        }

    }

    @RequestMapping(value = "/voteAmandman", method = RequestMethod.POST)
    public String voteAmandman(@RequestBody VoteDTO votes) throws TransformerException {
        System.out.println("Vote");
        DatabaseHelper db = new DatabaseHelper();
        Amandman am = db.findAmandmanById(votes.getId());
        if (votes.getGlasoviZa() > votes.getGlasoviProtiv() + votes.getGlasoviUzdrzani()) {

            List<ElementAmandmana> elementiAmandmana = am.getElementAmandmana();
            Akt akt = db.findAktById(am.getIdAkta());
            DocumentBuilderFactory dbFactoryAkt = DocumentBuilderFactory.newInstance();

            dbFactoryAkt.setNamespaceAware(true);
            DocumentBuilder dBuilderAkt = null;
            HashMap<String, String> prefMap = new HashMap<>();
            prefMap.put("ns3", "http://www.xmlProjekat.com/akt");
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


            for (ElementAmandmana element : elementiAmandmana) {
                String referenca = element.getReferencira();
                String akcija = element.getAkcija();
                System.out.println("Referenca je " + referenca);
                System.out.println("Akcija " + element.getAkcija());
                Node nodeElementAmandmana = null;
                Node clanNodeAmandmana = null;
                //umaarshall elementa amandmana
                try {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    dbFactory.setNamespaceAware(true);
                    DocumentBuilder dBuilderAmandman = null;
                    dBuilderAmandman = dbFactory.newDocumentBuilder();

                    Document docAmandman = dBuilderAmandman.newDocument();
                    JAXBContext jaxbContextAmandman = JAXBContext.newInstance(Amandman.class);
                    Marshaller marshallerAmandman = jaxbContextAmandman.createMarshaller();
                    marshallerAmandman.marshal(am, docAmandman);

                    HashMap<String, String> prefMapAmandman = new HashMap<>();
                    prefMapAmandman.put("ns2", "http://www.xmlProjekat.com/amandman");
                    //pretraga amandmana
                    XPathFactory xPathFactoryAmandman = XPathFactory.newInstance();
                    XPath xPathAmandman = xPathFactoryAmandman.newXPath();
                    xPathAmandman.setNamespaceContext(new NameSpaceContext(prefMapAmandman));
                    XPathExpression xPathExpression = xPathAmandman.compile("//ns2:ElementAmandmana[@Referencira = '" + referenca + "']");

                    nodeElementAmandmana = (Node) xPathExpression.evaluate(docAmandman, XPathConstants.NODE);
                    clanNodeAmandmana = nodeElementAmandmana.getChildNodes().item(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {

                    XPathFactory xPathFactory = XPathFactory.newInstance();
                    XPath xPath = xPathFactory.newXPath();
                    xPath.setNamespaceContext(new NameSpaceContext(prefMap));

                    //node za update ili dodavanje, mora se importovati u dokument akta
                    Node nodeUpd = null;
                    if (!akcija.equals("Obrisi")) {
                        nodeUpd = docAkt.importNode(clanNodeAmandmana, true);
                    }
                    if (referenca.contains("Clan")) {
                        XPathExpression xPathExpression = xPath.compile("//ns3:Clan[@Id = '" + referenca + "']");
                        Node node = (Node) xPathExpression.evaluate(docAkt, XPathConstants.NODE);
                        if (node != null) {

                            if (element.getAkcija().equals("Obrisi")) {
                                //node za ispis
                                Node proba = node.getParentNode();

                                //System.out.println("Broj dece pre brisanja = " + proba.getChildNodes().getLength());
                                //System.out.println("Pokrenuto brisanje");
                                node.getParentNode().removeChild(node);
                                //System.out.println("Broj dece posle brisanja = " + proba.getChildNodes().getLength());
                            } else if (element.getAkcija().equals("Izmeni")) {
                                //System.out.println("Pokrenuto menjanje");
                                //System.out.println("Text content pre " + node.getTextContent());
                                node.getParentNode().replaceChild(nodeUpd,node);
                                //System.out.println("Text content posle " + nodeUpd.getTextContent());
                            }
                        }
                    } else {
                        if (referenca.contains("Pododeljak")) {
                            XPathExpression xPathExpression = xPath.compile("//ns3:Pododeljak[@Id = '" + referenca + "']");
                            Node node = (Node) xPathExpression.evaluate(docAkt, XPathConstants.NODE);
                            System.out.println("Pokrenuto dodavenje u pododeljak");
                            //System.out.println("Broj dece pre dodavanja =" + node.getChildNodes().getLength());
                            node.appendChild(nodeUpd);
                            //System.out.println("Broj dece posle dodavanja =" + node.getChildNodes().getLength());
                        } else {
                            XPathExpression xPathExpression = xPath.compile("//ns3:Odeljak[@Id = '" + referenca + "']");
                            Node node = (Node) xPathExpression.evaluate(docAkt, XPathConstants.NODE);
                            //System.out.println("Broj dece pre dodavanja =" + node.getChildNodes().getLength());
                            node.appendChild(nodeUpd);
                            //System.out.println("Broj dece posle dodavanja =" + node.getChildNodes().getLength());


                        }

                    }

                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }


            }
            System.out.println("FILL IN IDS");
            //docAkt.getDocumentElement().normalize();
            //fillInIds(docAkt.getDocumentElement(), "/");
            //fillInBrojeve(docAkt.getDocumentElement(), "/");
            JAXBContext jc = null;
            try {
                jc = JAXBContext.newInstance(Akt.class);
                Unmarshaller u = jc.createUnmarshaller();

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                Akt a = (Akt) u.unmarshal(docAkt);
                db.writeAkt(a);
                //db.deleteAmandman(votes.getId());
            } catch (JAXBException e) {
                e.printStackTrace();
            }


            db.release();

            return "{ \"data\": \"Amandman primenjen!\" }";

        } else {


            db.deleteAmandman(votes.getId());

            db.release();

            return "{ \"data\": \"Amandman obrisan!\" }";
        }

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
            String nameKey =  subnode.getNodeName();

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

    private void fillInBrojeve(Node node, String parentsId) {

        Hashtable<String, Integer> namesCount = new Hashtable<String, Integer>();
        if (node.getNodeType() != Node.ELEMENT_NODE)
            return;

        if (!node.hasChildNodes())
            return;

        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node subnode = childNodes.item(i);
            //String nameKey2 = subnode.getNodeName();
            String nameKey = subnode.getLocalName();

            if (subnode.getNodeType() == Node.ELEMENT_NODE) {

                Integer count;
                Integer countAbsolute;
                Element elNode = (Element) subnode;

                if (namesCount.containsKey(nameKey)) {
                    count = namesCount.get(nameKey);
                    namesCount.put(nameKey, ++count);
                } else {
                    count = 1;
                    namesCount.put(nameKey, count);
                }
                //
                if (absoluteNamesCount.containsKey(nameKey)) {
                    countAbsolute = absoluteNamesCount.get(nameKey);
                    absoluteNamesCount.put(nameKey, ++countAbsolute);
                } else {
                    countAbsolute = 1;
                    absoluteNamesCount.put(nameKey, countAbsolute);
                }
                //

                if (elNode.getAttributeNode("Id") == null) {
                    fillInBrojeve(subnode, "/");

                } else {
                    System.out.println("pre" + elNode.getAttributeNode("Id"));
                    elNode.getAttributeNode("Id").setValue(parentsId + "/" + nameKey + count.toString());
                    if (elNode.getAttributeNode("RedniBroj")!=null) {
                        elNode.getAttributeNode("RedniBroj").setValue((countAbsolute.toString()));
                    }
                    else{
                        System.out.println("null je");
                    }
                    System.out.println("Broj " + count + elNode.getLocalName());
                    System.out.println("Broj abs " + countAbsolute + elNode.getLocalName());
                    System.out.println("Posle " + elNode.getAttributeNode("Id"));
                    fillInBrojeve(subnode, parentsId + "/" + nameKey + count.toString());
                }

            }
        }
    }



    @RequestMapping(value = "/voteUCelosti", method = RequestMethod.POST)
    public String voteUCelosti(@RequestBody VoteDTO votes) {
        DatabaseHelper db = new DatabaseHelper();
        Akt a = db.findAktById(votes.getId());

        if (votes.getGlasoviZa() > votes.getGlasoviProtiv() + votes.getGlasoviUzdrzani()) {
            a.setUCelostiZa(BigInteger.valueOf(votes.getGlasoviZa()));
            a.setUCelostiProtiv(BigInteger.valueOf(votes.getGlasoviProtiv()));
            a.setUCelostiUzdrzano(BigInteger.valueOf(votes.getGlasoviUzdrzani()));

            a.setStatus("Usvojen");

            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(new Date(System.currentTimeMillis()));
            XMLGregorianCalendar xmlGrogerianCalendar = null;
            try {
                xmlGrogerianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }

            a.setDatumGlasanja(xmlGrogerianCalendar);

            db.writeAkt(a);

            db.release();

            return "{ \"data\": \"Akt označen kao 'USVOJEN'!\" }";

        } else {
            //TODO: OBRISATI AMANDMANE BRISANOG AKTA

            db.deleteAkt(votes.getId());

            db.release();

            return "{ \"data\": \"Akt obrisan!\" }";

        }


    }
}