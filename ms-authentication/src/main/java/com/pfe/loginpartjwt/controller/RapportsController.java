package com.pfe.loginpartjwt.controller;

import com.pfe.loginpartjwt.models.Rapports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/rapports")
public class RapportsController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/allRapports")
    public List<Rapports> allQueries() {
        List<Rapports> listRapports = restTemplate.getForObject("http://requests-reports-saver/rapports/allRapports" , List.class);
        return listRapports;
    }


    @GetMapping("/addRapport/{iduser}/{titre}")
    public Rapports addRapport( @PathVariable("iduser") Long iduser,@PathVariable("titre") String titre) throws Exception {

       Rapports rapport =  restTemplate.getForObject("http://requests-reports-saver/rapports/addRapport/"+ iduser + "/" + titre,Rapports.class );
       return rapport;
    }

    @GetMapping("/addQueryRapport/{idrapport}/{idquery}")
    public Rapports addQueryRapport(@PathVariable("idrapport") Long idrapport,@PathVariable("idquery") Long idquery) throws Exception {

        Rapports rapport =  restTemplate.getForObject("http://requests-reports-saver/rapports/addQueryRapport/"+ idrapport + "/" + idquery,Rapports.class );
        return rapport;
    }


    @GetMapping("/addRapportOrganism/{idrapport}/{idorganism}")
    public void addAuthorization(@PathVariable("idrapport") Long idrapport, @PathVariable("idorganism") Long idorganism) throws Exception {

        restTemplate.getForObject("http://requests-reports-saver/rapports/addQueryRapport/"+ idrapport + "/" + idorganism,Rapports.class );

    }


    @DeleteMapping("/deleteRapport/{idrapport}")
    public void deleteUser(@PathVariable("idrapport") Long idrapport) throws Exception {

        restTemplate.delete("http://requests-reports-saver/rapports/deleteRapport/"+ idrapport);

    }


    @GetMapping("/addRapportUser/{idrapport}/{iduser}")
    public void addAuthorizationUser(@PathVariable("idrapport") Long idrapport, @PathVariable("iduser") Long iduser) throws Exception {

        restTemplate.getForObject("http://requests-reports-saver/rapports/addQueryRapport/"+ idrapport + "/" + iduser,Void.class );

    }


    @DeleteMapping("/deleteAuthorizationOthers/{idrapport}")
    public void deleteAuthorizarionOthers( @PathVariable("idrapport") Long idrapport) throws Exception {

        restTemplate.delete("http://requests-reports-saver/rapports/deleteRapport/"+ idrapport);

    }

}
