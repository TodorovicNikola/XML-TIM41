package xml_app.controller;

import org.springframework.web.bind.annotation.*;
import xml_app.model.Korisnik;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Vuletic on 23.5.2016.
 */
@RestController
@RequestMapping("/korisnici")
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

    /* PRIMER SA PARAMETROM */
    /*@RequestMapping(method = RequestMethod.GET)
    public Korisnik korisnikParametar(@RequestParam(value="id", defaultValue="4") String idStr){
        int id = Integer.parseInt(idStr);
        return new Korisnik("Ime","Prezime","email@mail.com","pass","user","064 111 111","Odbornik",id);
    }*/
}
