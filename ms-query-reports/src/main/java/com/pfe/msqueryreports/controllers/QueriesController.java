package com.pfe.msqueryreports.controllers;



import com.pfe.msqueryreports.models.Queries;
import com.pfe.msqueryreports.models.Rapports;
import com.pfe.msqueryreports.models.Users;
import com.pfe.msqueryreports.repositories.QueryRepository;
import com.pfe.msqueryreports.repositories.RapportsRepository;
import com.pfe.msqueryreports.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/queries")
public class QueriesController {

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    RapportsRepository rapportsRepository;

    @GetMapping("/allQueries")
    public List<Queries> allQueries() {
        return queryRepository.findAll();
    }

    @PostMapping("/saveQuery/{iduser}")
    public Queries saveQuery(@RequestBody Queries query, @PathVariable("iduser") Long iduser) throws Exception {

        if (userRepository.findById(iduser).isPresent() && !(queryRepository.findByTitre(query.getTitre()).isPresent())) {
            Queries Q = new Queries();
            Q.setDate_creation(new Date());
            Q.setTitre(query.getTitre());
            Q.setValeur(query.getValeur());
            Users U = userRepository.findById(iduser).get();
            Q.setCreator(U.getUsername());
            U.setListQueries(Q);
            return queryRepository.save(Q);
        } else {
            throw new Exception(
                    "no such username or query exists !!");
        }

    }

    @GetMapping("/addQueryOrganism/{idquery}/{idorganism}")
    public void addAuthorization(@PathVariable("idquery") Long idquery, @PathVariable("idorganism") Long idorganism) throws Exception {

        if (userRepository.findByIdorganism(idorganism).isPresent() && queryRepository.findById(idquery).isPresent()) {
            Queries q = queryRepository.findById(idquery).get();
            List<Users> U = userRepository.findByIdorganism(idorganism).get();
            for (Users u : U) {
                if (!(u.getListQueries().contains(q))) {
                    u.setListQueries(q);
                    userRepository.save(u);
                }
            }
        } else {
            throw new Exception(
                    "not found organism or query !"
            );

        }
    }


    @DeleteMapping("/deleteQuery/{idquery}")
    public void deleteUser(@PathVariable("idquery") Long idquery) throws Exception {

        if (!(queryRepository.findById(idquery).isPresent())) {
            throw new Exception(
                    "This query  doesn't exists !! "
            );
        } else {
            List<Rapports> rapports = rapportsRepository.findAll();
            List<Users> liste = userRepository.findAll();
            for (Users user : liste
            ) {
                user.getListQueries().remove(queryRepository.findById(idquery).get());
            }
            for (Rapports rapport : rapports
            ) {
                rapport.getListQueries().remove(queryRepository.findById(idquery).get());
            }

            queryRepository.delete(queryRepository.findById(idquery).get());
        }
    }

    @PutMapping("/UpdateQuery/{idquery}")
    public void updateUser(@RequestBody Queries query, @PathVariable("idquery") Long idquery) throws Exception {

        if (!(queryRepository.findById(idquery).isPresent())) {
            throw new Exception(
                    "This query  doesn't exists"
            );
        } else {
            Queries q = queryRepository.findById(idquery).get();
            if (query.getTitre() != null) q.setTitre(query.getTitre());
            if (query.getValeur() != null) q.setValeur(query.getValeur());
            queryRepository.save(q);
        }
    }

    @DeleteMapping("/deleteAuthorization/{idorganism}/{idquery}")
    public void deleteAuthorizarion(@PathVariable("idorganism") Long idorganism, @PathVariable("idquery") Long idquery) throws Exception {

        if (!(queryRepository.findById(idquery).isPresent())) {
            throw new Exception(
                    "This query doesn't exists"
            );
        } else if (userRepository.findByIdorganism(idorganism).isPresent()){
            List<Users> liste = userRepository.findByIdorganism(idorganism).get();
            for (Users user : liste
            ) {
                user.getListQueries().remove(queryRepository.findById(idquery).get());
                userRepository.save(user);
            }
        } else {
            throw new Exception(
                    "organism not found"
            );
        }

    }

    @GetMapping("/addQueryUser/{idquery}/{iduser}")
    public void addAuthorizationUser(@PathVariable("idquery") Long idquery, @PathVariable("iduser") Long iduser) throws Exception {

        if (userRepository.findById(iduser).isPresent() && queryRepository.findById(idquery).isPresent()) {
            Queries q = queryRepository.findById(idquery).get();
            Users U = userRepository.findById(iduser).get();
            if(!(U.getListQueries().contains(q))) {
                U.setListQueries(q);
                userRepository.save(U);
            }
        } else {
            throw new Exception(
                    "not found organism or query !"
            );

        }
    }

    @DeleteMapping("/deleteAuthorizationUser/{iduser}/{idquery}")
    public void deleteAuthorizarionUser(@PathVariable("iduser") Long iduser, @PathVariable("idquery") Long idquery) throws Exception {

        if (!(queryRepository.findById(idquery).isPresent())) {
            throw new Exception(
                    "This query doesn't exists"
            );
        } else if (userRepository.findById(iduser).isPresent()){
                Users U = userRepository.findById(iduser).get();
                U.getListQueries().remove(queryRepository.findById(idquery).get());
                userRepository.save(U);

            } else {
            throw new Exception(
                    "organism not found"
            );
        }

    }


    @DeleteMapping("/deleteAuthorizationOthers/{idquery}")
    public void deleteAuthorizarionOthers( @PathVariable("idquery") Long idquery) throws Exception {

        if (!(queryRepository.findById(idquery).isPresent())) {
            throw new Exception(
                    "This query doesn't exists"
            );
        } else {
           String createur = queryRepository.findById(idquery).get().getCreator();
           List<Users> liste = userRepository.findAllByListQueries(queryRepository.findById(idquery).get()).get();
            for (Users u: liste
                 ) {
                if (!(u.getUsername().equals(createur))) {
                    u.getListQueries().remove(queryRepository.findById(idquery).get());
                    userRepository.save(u);
                }
            }
        }

    }

}