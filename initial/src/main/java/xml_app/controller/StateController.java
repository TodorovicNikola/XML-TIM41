package xml_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml_app.database.DatabaseHelper;

@RestController
@RequestMapping("/api/state")
public class StateController {

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
}
