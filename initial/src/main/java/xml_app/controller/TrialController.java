package xml_app.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml_app.model.Amandman;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Created by Vuletic on 25.5.2016.
 */

@RestController
@RequestMapping("/api/trial")
public class TrialController {

    @RequestMapping(value = "/prodji",method = RequestMethod.POST)
    public Amandman trial(@RequestBody String telo) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Amandman.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(telo);

        try{
            Amandman a = (Amandman) unmarshaller.unmarshal(reader);
            return a;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
