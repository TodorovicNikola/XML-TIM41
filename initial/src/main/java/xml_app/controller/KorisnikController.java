package xml_app.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import xml_app.model.Korisnik;

import java.util.ArrayList;
import java.util.Collection;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * Created by Vuletic on 23.5.2016.
 */
@RestController
@RequestMapping("/api/korisnici")
public class KorisnikController {

    /* PRIMER VRACANJA KOLEKCIJE*/
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Korisnik> sviKorisnici(){
        ArrayList<Korisnik> korisnici = new ArrayList<>();
        korisnici.add(new Korisnik("Ime","Prezime","email@mail.com","pass","user","064 111 111","Odbornik",111));
        korisnici.add(new Korisnik("PPP","REEEasdDD","email@mail.com","pass","user","","Predsednik",32));
        return korisnici;
    }

    /* PRIMER VRACANJA KONKRETNOG */
    @RequestMapping(value = "/{korisnikId}", method = RequestMethod.GET)
    public Korisnik korisnik(@PathVariable int korisnikId) {
        return new Korisnik("Ime","Prezime","email@mail.com","pass","user","064 111 111","Odbornik",korisnikId);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password ) {

        byte[] encodedBytes = Base64.encodeBase64("Test".getBytes());
        String test = new String(encodedBytes);

        Korisnik k = new Korisnik("Ime","Prezime","email@mail.com","pass","user","064 111 111","Odbornik",15);

        if(username.equals(k.getKorisnickoIme()) && password.equals(k.getLozinka())){
            JSONObject payload = new JSONObject();
            payload.put("sub",k.getKorisnickoIme());
            payload.put("role",k.getTip());
            payload.put("name",k.getIme() + " " + k.getPrezime());

            String jwt = (Jwts.builder().setPayload(payload.toJSONString()).signWith(SignatureAlgorithm.HS512, test).compact());
            System.out.println(jwt);
            return jwt;
        }else{
            return null;
        }

    }

    /* PRIMER SA PARAMETROM */
    /*@RequestMapping(method = RequestMethod.GET)
    public Korisnik korisnikParametar(@RequestParam(value="id", defaultValue="4") String idStr){
        int id = Integer.parseInt(idStr);
        return new Korisnik("Ime","Prezime","email@mail.com","pass","user","064 111 111","Odbornik",id);
    }*/
}
