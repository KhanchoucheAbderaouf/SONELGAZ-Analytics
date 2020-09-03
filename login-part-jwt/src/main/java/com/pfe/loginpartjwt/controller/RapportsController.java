package com.pfe.loginpartjwt.controller;

import com.pfe.loginpartjwt.models.Queries;
import com.pfe.loginpartjwt.models.Rapports;
import com.pfe.loginpartjwt.models.Users;
import com.pfe.loginpartjwt.repositories.QueryRepository;
import com.pfe.loginpartjwt.repositories.RapportsRepository;
import com.pfe.loginpartjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
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

    @PostMapping("/addRapport/{iduser}")
    public Rapports addRapport(@RequestBody Rapports rapport, @PathVariable("iduser") Long iduser) throws Exception {

        if (userRepository.findById(iduser).isPresent()) {
            Rapports R = new Rapports();
            R.setTitre(rapport.getTitre());
            Users U = userRepository.findById(iduser).get();
            R.setCreator(U.getUsername());
            R.setListUsers(U);
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
                if (!(R.getListUsers().contains(u))) {
                    R.setListUsers(u);
                    rapportsRepository.save(R);
                }
            }
        } else {
            throw new Exception(
                    "not found organism or query !"
            );

        }
    }


    @DeleteMapping("/deleteRapport/{idrapport}")
    public void deleteUser(@PathVariable("idrapport") Long idrapport) throws Exception {

        if (!(queryRepository.findById(idrapport).isPresent())) {
            throw new Exception(
                    "This rapport  doesn't exists !! "
            );
        } else {
             queryRepository.delete(queryRepository.findById(idrapport).get());
        }
    }


    @GetMapping("/addRapportUser/{idrapport}/{iduser}")
    public void addAuthorizationUser(@PathVariable("idrapport") Long idrapport, @PathVariable("iduser") Long iduser) throws Exception {

        if (userRepository.findById(iduser).isPresent() && rapportsRepository.findById(idrapport).isPresent()) {
            Rapports R = rapportsRepository.findById(idrapport).get();
            Users U = userRepository.findById(iduser).get();
            if(!(R.getListUsers().contains(U))) {
                R.setListUsers(U);
                rapportsRepository.save(R);
            }
        } else {
            throw new Exception(
                    "not found organism or query !"
            );

        }
    }


    @DeleteMapping("/deleteAuthorizationOthers/{idrapports}")
    public void deleteAuthorizarionOthers( @PathVariable("idrapport") Long idrapport) throws Exception {

        if (!(rapportsRepository.findById(idrapport).isPresent())) {
            throw new Exception(
                    "This query doesn't exists"
            );
        } else {
            String createur = rapportsRepository.findById(idrapport).get().getCreator();
            Rapports R = rapportsRepository.findById(idrapport).get();
            List<Users> users = R.getListUsers();
            for (Users u: users) {
                if (!(u.getUsername().equals(createur))) {
                    R.getListQueries().remove(u);
                    rapportsRepository.save(R);
                }
            }
        }

    }

}
