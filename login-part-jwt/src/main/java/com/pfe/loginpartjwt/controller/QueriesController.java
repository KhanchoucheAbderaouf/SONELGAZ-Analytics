package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.Queries;
import com.pfe.loginpartjwt.models.Users;
import com.pfe.loginpartjwt.repositories.QueryRepository;
import com.pfe.loginpartjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/queries")
public class QueriesController {

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/allQueries")
    public List<Queries> allQueries() {
        return queryRepository.findAll();
    }

    @PostMapping("/saveQuery/{username}")
    public void saveQuery(@RequestBody Queries query, @PathVariable("username") String username) throws Exception {
        Queries q = new Queries();
        Users u = new Users();
        if (userRepository.findByUsername(username).isPresent() && !(queryRepository.findByTitre(query.getTitre()).isPresent())) {
            Queries Q = new Queries();
            Q.setDate_creation(new Date());
            Q.setTitre(query.getTitre());
            Q.setValeur(query.getValeur());
            Users U = userRepository.findByUsername(username).get();
            Q.setCreator(U.getUsername());
            U.setListQueries(Q);
            queryRepository.save(Q);
        } else {
            throw new Exception(
                    "no such username or query exists !!");
        }

          /*  Long idquery = queryRepository.findByTitre(q.getTitre()).get().getIdquery();

            queryRepository.saveRef(u.getIduser(), idquery);
*/
    }

    @PostMapping("/addQueryUser/{idquery}/{idorganism}")
    public void addAuthorization(@PathVariable("idquery") Long idquery, @PathVariable("idorganism") Long idorganism) throws Exception {

        if (userRepository.findByIdorganism(idorganism).isPresent() && queryRepository.findById(idquery).isPresent()) {
            Queries q = queryRepository.findById(idquery).get();
            System.out.println(q.getIdquery() + " " + q.getCreator() + " " + q.getTitre());
            List<Users> U = userRepository.findByIdorganism(idorganism).get();
            for (Users u : U) {
                if (u.getListQueries().contains(q)) {
                    throw new Exception(
                            "query already accessed by this organism !"
                    );
                } else {
                    u.setListQueries(q);
                    userRepository.save(u);
                }
            }
        }
    }


    @DeleteMapping("/deleteQuery/{titre}")
    public void deleteUser(@PathVariable("titre") String titre) throws Exception {

        if (!(queryRepository.findByTitre(titre).isPresent())) {
            throw new Exception(
                    "This query : " + titre + " doesn't exists"
            );
        } else {
            List<Users> liste = userRepository.findAll();
            for (Users user : liste
            ) {
                user.getListQueries().remove(queryRepository.findByTitre(titre).get());
            }

            queryRepository.delete(queryRepository.findByTitre(titre).get());
        }
    }

    @PutMapping("/UpdateQuery/{idquery}")
    public void deleteUser(@RequestBody Queries query, @PathVariable("idquery") Long idquery) throws Exception {

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



}