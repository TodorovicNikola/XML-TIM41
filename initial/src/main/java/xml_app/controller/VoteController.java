package xml_app.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml_app.database.DatabaseHelper;
import xml_app.model.Akt;
import xml_app.model.Amandman;
import xml_app.model.DTOs.VoteDTO;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
    @RequestMapping(value="/voteUNacelu", method = RequestMethod.POST)
    public String voteUNacelu(@RequestBody VoteDTO votes){
        DatabaseHelper db = new DatabaseHelper();
        Akt a = db.findAktById(votes.getId());

        if(votes.getGlasoviZa() > votes.getGlasoviProtiv() + votes.getGlasoviUzdrzani()){
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
        }else{
            //TODO: OBRISATI AMANDMANE BRISANOG AKTA
            db.deleteAkt(votes.getId());

            db.release();

            return "{ \"data\": \"Akt obrisan!\" }";

        }

    }

    @RequestMapping(value="/voteAmandman", method = RequestMethod.POST)
    public String voteAmandman(@RequestBody VoteDTO votes){
        DatabaseHelper db = new DatabaseHelper();
        Amandman am = db.findAmandmanById(votes.getId());
        if(votes.getGlasoviZa() > votes.getGlasoviProtiv() + votes.getGlasoviUzdrzani()) {
            //TODO: PRIMENITI AMANDMAN



            db.release();

            return "{ \"data\": \"Amandman primenjen!\" }";

        }else{


            db.deleteAmandman(votes.getId());

            db.release();

            return "{ \"data\": \"Amandman obrisan!\" }";
        }

    }

    @RequestMapping(value="/voteUCelosti", method = RequestMethod.POST)
    public String voteUCelosti(@RequestBody VoteDTO votes){
        DatabaseHelper db = new DatabaseHelper();
        Akt a = db.findAktById(votes.getId());

        if(votes.getGlasoviZa() > votes.getGlasoviProtiv() + votes.getGlasoviUzdrzani()){
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

        }else{
            //TODO: OBRISATI AMANDMANE BRISANOG AKTA

            db.deleteAkt(votes.getId());

            db.release();

            return "{ \"data\": \"Akt obrisan!\" }";

        }



    }
}