package com.pfe.loginpartjwt.controller;



import com.pfe.loginpartjwt.models.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/queries")
public class QueriesController {




    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/allQueries")
    public List<Queries> allQueries() {

        List<Queries> listQueries = restTemplate.getForObject("http://requests-reports-saver/queries/allQueries" , List.class);
        return listQueries;
    }

    @PostMapping("/saveQuery/{iduser}")
    public Queries saveQuery(@RequestBody Queries query, @PathVariable("iduser") Long iduser) throws Exception {

        Queries saving =  restTemplate.postForObject("http://requests-reports-saver/queries/saveQuery/" + iduser, query ,Queries.class);

        return saving;
    }

    @GetMapping("/addQueryOrganism/{idquery}/{idorganism}")
    public void addAuthorization(@PathVariable("idquery") Long idquery, @PathVariable("idorganism") Long idorganism) throws Exception {

         restTemplate.getForEntity("http://requests-reports-saver/queries/addQueryOrganism/" + idquery +"/" + idorganism,Void.class);

    }


    @DeleteMapping("/deleteQuery/{idquery}")
    public void deleteUser(@PathVariable("idquery") Long idquery) throws Exception {

        restTemplate.delete("http://requests-reports-saver/queries/deleteQuery/" + idquery ,Void.class);
    }

    @PutMapping("/UpdateQuery/{idquery}")
    public void updateUser(@RequestBody Queries query, @PathVariable("idquery") Long idquery) throws Exception {

    restTemplate.put("http://requests-reports-saver/queries/UpdateQuery/" + idquery,query);

    }

    @DeleteMapping("/deleteAuthorization/{idorganism}/{idquery}")
    public void deleteAuthorizarion(@PathVariable("idorganism") Long idorganism, @PathVariable("idquery") Long idquery) throws Exception {

        restTemplate.delete("http://requests-reports-saver/queries/deleteAuthorization/" + idorganism + "/" + idquery ,Void.class);

    }

    @GetMapping("/addQueryUser/{idquery}/{iduser}")
    public void addAuthorizationUser(@PathVariable("idquery") Long idquery, @PathVariable("iduser") Long iduser) throws Exception {
        restTemplate.getForEntity("http://requests-reports-saver/queries/addQueryUser/" + idquery +"/" + iduser,Void.class);
    }

    @DeleteMapping("/deleteAuthorizationUser/{iduser}/{idquery}")
    public void deleteAuthorizarionUser(@PathVariable("iduser") Long iduser, @PathVariable("idquery") Long idquery) throws Exception {

        restTemplate.delete("http://requests-reports-saver/queries/deleteAuthorizationUser/" + iduser + "/" + idquery ,Void.class);

    }


    @DeleteMapping("/deleteAuthorizationOthers/{idquery}")
    public void deleteAuthorizarionOthers( @PathVariable("idquery") Long idquery) throws Exception {

        restTemplate.delete("http://requests-reports-saver/queries/deleteAuthorizationOthers/" + idquery ,Void.class);
    }

}