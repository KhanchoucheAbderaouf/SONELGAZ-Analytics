package com.pfe.loginpartjwt.controller;


import com.pfe.loginpartjwt.models.JSONAnswer;
import com.pfe.loginpartjwt.models.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/getOneResult/{title}")
    public List<Map<String, Object>> Requests(@PathVariable("title") String title) {
        System.out.println(title);
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://ms-mongodb/mongo/getOneResult/" + title, JSONAnswer.class);
        System.out.println(jsonAnswer.getJsonAnswer());
        return jsonAnswer.getJsonAnswer();
    }

    @PostMapping(value = "/addResult")
    public void Requests(@RequestBody Results results) {
        restTemplate.postForObject("http://ms-mongodb/mongo/addResult", results ,Results.class);
    }

}
