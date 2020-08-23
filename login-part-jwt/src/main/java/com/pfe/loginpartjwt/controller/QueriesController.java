package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.Queries;
import com.pfe.loginpartjwt.models.Users;
import com.pfe.loginpartjwt.repositories.QueryRepository;
import com.pfe.loginpartjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/queries")
public class QueriesController {

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/saveQuery/{username}")
    public void saveQuery(@RequestBody Queries query, @PathVariable("username") String username) throws Exception {

        if (userRepository.findByUsername(username).isPresent()) {
            Queries Q = new Queries();
            Q.setDate_creation(new Date());
            Q.setTitre(query.getTitre());
            Q.setValeur(query.getValeur());
            Users U = userRepository.findByUsername(username).get();
            U.setListqueries(Q);
            try {
                queryRepository.saveQuery(Q.getTitre(), Q.getValeur(), U.getIduser());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            throw new Exception(
                    "no such username");
        }

    }




}
