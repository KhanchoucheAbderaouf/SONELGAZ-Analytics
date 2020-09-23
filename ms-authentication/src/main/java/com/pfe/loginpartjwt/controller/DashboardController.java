package com.pfe.loginpartjwt.controller;

import com.pfe.loginpartjwt.models.Dashboard;
import com.pfe.loginpartjwt.models.JSONAnswer;
import com.pfe.loginpartjwt.models.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/getOneResult/{title}")
    public List<Map<String, Object>> Requests(@PathVariable("title") String title) {
        System.out.println(title);
        JSONAnswer jsonAnswer =
                restTemplate.getForObject("http://ms-mongodb/dashboard/getOneResult/" + title, JSONAnswer.class);
        System.out.println(jsonAnswer.getJsonAnswer());
        return jsonAnswer.getJsonAnswer();
    }

    @PostMapping(value = "/addResult")
    public void Requests(@RequestBody Dashboard dashboard) {
        restTemplate.postForObject("http://ms-mongodb/dashboard/addResult", dashboard ,Dashboard.class);
    }
}
