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

    @GetMapping(value = "/causes")
    public List<Map<String, Object>> RequestCauses() {
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/causes", JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }


    @GetMapping(value = "/objectif")
    public List<Map<String, Object>> RequestObjectif() {
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/objectif", JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }

    @GetMapping(value = "/regime")
    public List<Map<String, Object>> RequestRegime() {
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/regime", JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }

    @GetMapping(value = "/reseau")
    public List<Map<String, Object>> RequestReseau() {
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/reseau", JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }

    @GetMapping(value = "/typeevent")
    public List<Map<String, Object>> RequestTypeEvent() {
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/typeevent", JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }

    @GetMapping(value = "/typeobjectif")
    public List<Map<String, Object>> RequestTypeObjectif() {
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/typeobjectif", JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }

    @GetMapping(value = "/typesaisieobjectif")
    public List<Map<String, Object>> RequestTypeSaisieObjectif() {
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/typesaisieobjectif", JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }

    @GetMapping(value = "/typecentrale")
    public List<Map<String, Object>> RequestTypeCentrale() {
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/typecentrale", JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }


    @GetMapping(value = "/organism/{organism}")
    public List<Map<String, Object>> RequestOrganism(@PathVariable("organism") String organism) {
        System.out.println(organism);
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/organism/" + organism, JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }
    @GetMapping(value = "/codeorganism/{organism}")
    public List<Map<String, Object>> RequestCodeOrganism(@PathVariable("organism") Long organism) {
        System.out.println(organism);
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://request-holder/requests/codeorganism/" + organism, JSONAnswer.class);
        return jsonAnswer.getJsonAnswer();
    }

}
