package xml_app.controller;

import org.springframework.web.bind.annotation.*;
import xml_app.database.DatabaseHelper;
import xml_app.model.Akt;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;

/**
 * Created by Vuletic on 25.5.2016.
 */

@RestController
@RequestMapping("/api/akti")
public class AktController {

    @RequestMapping(value="/usvojeni", method = RequestMethod.GET)
    public Collection<Akt> usvojeniAkti(){
        DatabaseHelper db = new DatabaseHelper();

        List<Akt> akti = db.getUsvojeniAkti();
        db.release();
        return akti;
    }

    @RequestMapping(value="/u-proceduri", method = RequestMethod.GET)
    public Collection<Akt> aktiUProceduri(){
        DatabaseHelper db = new DatabaseHelper();

        List<Akt> akti = db.getAktiUProceduri();
        db.release();
        return akti;
    }

    @RequestMapping(value = "/{aktId}",method = RequestMethod.GET)
    public Akt konkretanAkt(@PathVariable int aktId){
        DatabaseHelper db = new DatabaseHelper();

        Akt a = db.findAktById(aktId);

        db.release();

        return a;

    }

    @RequestMapping(value = "/dodaj",method = RequestMethod.POST)
    public Akt trial(@RequestBody String telo) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Akt.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(telo);

        try{
            DatabaseHelper db = new DatabaseHelper();
            Akt a = (Akt) unmarshaller.unmarshal(reader);

            db.writeAkt(a);

            return a;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
