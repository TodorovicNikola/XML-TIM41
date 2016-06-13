package xml_app.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    @RequestMapping(value="/voteUNacelu", method = RequestMethod.POST)
    public String voteUNacelu(@RequestBody String str){
        //return "{ \"data\": \"" + idAkta + glasoviZa + glasoviProtiv + glasoviUzdrzani + "\" }";
        int a;
        a = 50;
        return str;
    }
}
