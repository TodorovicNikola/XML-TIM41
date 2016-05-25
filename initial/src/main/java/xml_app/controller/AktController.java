package xml_app.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml_app.model.Akt;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Vuletic on 25.5.2016.
 */

@RestController
@RequestMapping("/api/akti")
public class AktController {

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Akt> sviAkti(){
        ArrayList<Akt> akti = new ArrayList<>();
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(Akt.class);
            Unmarshaller u = jc.createUnmarshaller();
            Object o = u.unmarshal( new File( "xml\\Akt.xml" ) );
            Akt a = (Akt) o;
            Object ob = u.unmarshal( new File( "xml\\Akt2.xml" ) );
            Akt ak = (Akt) ob;
            akti.add(a);
            akti.add(ak);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return akti;
    }

    @RequestMapping(value = "/{aktId}\"",method = RequestMethod.GET)
    public Akt konkretanAkt(@PathVariable int aktId){
        return null;
    }

}
