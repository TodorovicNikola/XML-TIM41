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
                //TODO: provera da li su svi izglasani u nacelu
                db.nextState();
            } break;
            case "glasanjeZaAmandmane": {
                //TODO: provera da li su svi izglasani
                db.nextState();
            } break;
            case "glasanjeUCelosti": {
                //TODO: provera da li su svi izglasani u celosti
                db.nextState();
            } break;
            default: { db.nextState(); }
            break;
        }

        String retVal = "{ \"data\": \"" + db.getState() + "\" }";

        db.release();

        return retVal;
        db.release();
    }
}
