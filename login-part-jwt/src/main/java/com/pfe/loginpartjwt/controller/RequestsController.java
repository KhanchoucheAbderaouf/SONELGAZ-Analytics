package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.JSONAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/requests")
public class RequestsController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/{req}")
    public List<Map<String, Object>> Requests(@PathVariable("req") String req) {
        System.out.println(req);
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/" + req, JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }

}
