package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.Queries;
import com.pfe.loginpartjwt.models.Users;
import com.pfe.loginpartjwt.repositories.QueryRepository;
import com.pfe.loginpartjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/queries")
public class QueriesController {

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/saveQuery/{username}")
    public void saveQuery(@RequestBody Queries query, @PathVariable("username") String username) throws Exception {

        if (userRepository.findByUsername(username).isPresent()){
            Queries Q = new Queries();
            Q.setDate_creation(new Date());
            Q.setTitre(query.getTitre());
            Q.setValeur(query.getValeur());
            Users U = userRepository.findByUsername(username).get();
            Q.setCreator(U.getUsername());
            try {
               queryRepository.saveQuery(Q.getTitre(), Q.getValeur(), Q.getCreator(),U.getIduser());
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            throw new Exception(
                    "no such username !!");
        }


    }

    @PostMapping("/addQueryUser/{idquery}/{idorganism}")
    public void addAuthorization(@PathVariable("idquery") Long idquery, @PathVariable("idorganism") Long idorganism) throws Exception {

        if (userRepository.findByIdorganism(idorganism).isPresent() && queryRepository.findById(idquery).isPresent()) {
            Queries q = queryRepository.findById(idquery).get();
            Users U = userRepository.findByIdorganism(idorganism).get();


            try {
                queryRepository.saveQuery(q.getTitre(), q.getValeur(), q.getCreator(),U.getIduser());
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            throw new Exception(
                    "no such username or query !!");
        }

    }


    @DeleteMapping("/deleteQuery/{titre}")
    public void deleteUser(@PathVariable("titre") String titre) throws Exception {

        if(!(queryRepository.findFirstByTitre(titre).isPresent())) {
            throw new Exception(
                    "This query : " + titre + " doesn't exists"
            );}
        else{
            queryRepository.deleteAll(queryRepository.findAllByTitre(titre).get());
        }
    }
}
