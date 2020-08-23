package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.AuthenticationRequest;
import com.pfe.loginpartjwt.models.JSONAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/requests")
public class RequestsController {

    @Autowired
    private RestTemplate restTemplate;



    @GetMapping(value = "/{req}", consumes = "application/json", produces = "application/json")
    public JSONAnswer Requests(@PathVariable("req") String req) {
        System.out.println(req);
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/" + req, JSONAnswer.class);
        return jsonAnswer;
    }

}
