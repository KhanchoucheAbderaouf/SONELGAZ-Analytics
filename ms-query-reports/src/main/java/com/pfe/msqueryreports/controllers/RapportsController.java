package com.pfe.msqueryreports.controllers;


import com.pfe.msqueryreports.models.Queries;
import com.pfe.msqueryreports.models.Rapports;
import com.pfe.msqueryreports.models.Users;
import com.pfe.msqueryreports.repositories.QueryRepository;
import com.pfe.msqueryreports.repositories.RapportsRepository;
import com.pfe.msqueryreports.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/rapports")
public class RapportsController {

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RapportsRepository rapportsRepository;

    @GetMapping("/allRapports")
    public List<Rapports> allQueries() {
        return rapportsRepository.findAll() ;
    }

    @GetMapping("/addRapport/{iduser}/{titre}")
    public Rapports addRapport( @PathVariable("iduser") Long iduser,@PathVariable("titre") String titre) throws Exception {

        if (userRepository.findById(iduser).isPresent()) {
            Rapports R = new Rapports();
            R.setTitre(titre);
            Users U = userRepository.findById(iduser).get();
            R.setCreator(U.getUsername());
            U.setListRapports(R);
            return rapportsRepository.save(R);
        } else {
            throw new Exception(
                    "no such username or query exists !!");
        }
    }

    @GetMapping("/addQueryRapport/{idrapport}/{idquery}")
    public Rapports addQueryRapport(@PathVariable("idrapport") Long idrapport,@PathVariable("idquery") Long idquery) throws Exception {
        if (queryRepository.findById(idquery).isPresent()) {
            Rapports R =  rapportsRepository.findById(idrapport).get();
            if(!(R.getListQueries().contains(queryRepository.findById(idquery).get()))) {
                R.setListQueries(queryRepository.findById(idquery).get());
            }else {
                throw new Exception(
                        "Query already exist on this rapport !!");
            }
            return rapportsRepository.save(R);
        } else {
            throw new Exception(
                    "no such username or query exists !!");
        }
    }


    @GetMapping("/addRapportOrganism/{idrapport}/{idorganism}")
    public void addAuthorization(@PathVariable("idrapport") Long idrapport, @PathVariable("idorganism") Long idorganism) throws Exception {

        if (userRepository.findByIdorganism(idorganism).isPresent() && rapportsRepository.findById(idrapport).isPresent()) {
            Rapports R = rapportsRepository.findById(idrapport).get();
            List<Users> U = userRepository.findByIdorganism(idorganism).get();
            for (Users u : U) {
                if (!(u.getListRapports().contains(R))) {
                    u.setListRapports(R);
                    userRepository.save(u);
                }
            }
        } else {
            throw new Exception(
                    "not found organism or query !"
            );

        }
    }


    @DeleteMapping("/deleteQueriesRapport/{idrapport}")
    public void deleteAllQueries(@PathVariable("idrapport") Long idrapport) throws Exception {

        if (!(rapportsRepository.findById(idrapport).isPresent())) {
            throw new Exception(
                    "This rapport  doesn't exists !! "
            );
        } else {
            List<Queries> queries = new ArrayList<Queries>();
            Rapports R = rapportsRepository.findById(idrapport).get();
            R.setListQueries2(null);
            rapportsRepository.save(R);
        }
    }


    @DeleteMapping("/deleteRapport/{idrapport}")
    public void deleteRapport(@PathVariable("idrapport") Long idrapport) throws Exception {

        if (!(rapportsRepository.findById(idrapport).isPresent())) {
            throw new Exception(
                    "This rapport  doesn't exists !! "
            );
        } else {
            List<Users> liste = userRepository.findAll();
            for (Users user : liste
            ) {
                user.getListRapports().remove(rapportsRepository.findById(idrapport).get());
            }
            deleteAllQueries(idrapport);
            rapportsRepository.delete(rapportsRepository.findById(idrapport).get());
        }
    }

    @GetMapping("/addRapportUser/{idrapport}/{iduser}")
    public void addAuthorizationUser(@PathVariable("idrapport") Long idrapport, @PathVariable("iduser") Long iduser) throws Exception {

        if (userRepository.findById(iduser).isPresent() && rapportsRepository.findById(idrapport).isPresent()) {
            Rapports R = rapportsRepository.findById(idrapport).get();
            Users U = userRepository.findById(iduser).get();
            if(!(U.getListRapports().contains(R))) {
                U.setListRapports(R);
                userRepository.save(U);
            }
        } else {
            throw new Exception(
                    "not found organism or query !"
            );

        }
    }


    @DeleteMapping("/deleteAuthorizationOthers/{idrapport}")
    public void deleteAuthorizarionOthers( @PathVariable("idrapport") Long idrapport) throws Exception {

        if (!(rapportsRepository.findById(idrapport).isPresent())) {
            throw new Exception(
                    "This query doesn't exists"
            );
        } else {
            String createur = rapportsRepository.findById(idrapport).get().getCreator();
            Rapports R = rapportsRepository.findById(idrapport).get();

            List<Users> users = userRepository.findAll();
            for (Users u: users) {
                if (!(u.getUsername().equals(createur))) {
                   if(u.getListRapports().contains(R)){
                       u.getListRapports().remove(R);
                       userRepository.save(u);
                   }
                }
            }
        }

    }

}
