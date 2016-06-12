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
        String ret = db.getState();
        db.release();

        return ret;
    }

    @RequestMapping(value="/nextState", method = RequestMethod.GET)
    public void nextState(){
        DatabaseHelper db = new DatabaseHelper();

        db.nextState();
        db.release();
    }

}
