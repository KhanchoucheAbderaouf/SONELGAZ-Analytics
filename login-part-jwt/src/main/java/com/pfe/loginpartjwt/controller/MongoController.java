package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;


@RestController
@CrossOrigin
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/getOneResult/{title}")
    public String Requests(@PathVariable("title") String title) {
        System.out.println(title);
        String jsonAnswer =
                restTemplate.getForObject("http://ms-mongodb/mongo/getOneResult/" + title, String.class);
        return jsonAnswer;
    }

    @PostMapping(value = "/addResult")
    public void Requests(@RequestBody LinkedHashMap results) {
        restTemplate.postForObject("http://ms-mongodb/mongo/addResult", results ,LinkedHashMap.class);
    }

}
