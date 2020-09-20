package com.pfe.springtest.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.pfe.springtest.model.JSONAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;



@RestController
@CrossOrigin
@RequestMapping("/requests")
public class MainController {


    @Autowired
    private RestTemplate restTemplate;

/*
    @Autowired
    WebClient.Builder webClientBuilder;
*/


    @Autowired
    private JdbcTemplate jdbcTemplate;


    /*@HystrixCommand(fallbackMethod = "getFallbackRequest",
            commandProperties = {
                        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
                        @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "5"),
                        @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "50"),
                        @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
            }
            bulkhead pattern

            fallbackmethod .., threadPoolKey = "geg",threadPoolProperties={
                        @HystrixProperty(name="coreSize",value = "20"),
                        @HystrixProperty(name="maxQueueSize",value = "10"),

        )*/
    @GetMapping("/codeorganism/{organism}")
    public JSONAnswer RequestCodeOrganism(@PathVariable("organism") Long organism) {
        System.out.println(organism);
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList("select * from bi.dim_organisme where code_organisme = " + organism));
        System.out.println(a.getJsonAnswer());
        return a;
    }



    @GetMapping("/organism/{organism}")
    public JSONAnswer RequestOrganism(@PathVariable("organism") String organism) {
        System.out.println(organism);
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList("select * from bi.dim_organisme where " + organism));
        System.out.println(a.getJsonAnswer());
        return a;
    }


    @GetMapping("/causes")
    public JSONAnswer RequestCauses() {
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList("select * from bi.dim_cause "));
        System.out.println(a.getJsonAnswer());
        return a;
    }

    @GetMapping("/objectif")
    public JSONAnswer RequestObjectif() {
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList("select * from bi.dim_objectif "));
        System.out.println(a.getJsonAnswer());
        return a;
    }

    @GetMapping("/regime")
    public JSONAnswer RequestRegime() {
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList("select * from bi.dim_regime_fct "));
        System.out.println(a.getJsonAnswer());
        return a;
    }


    @GetMapping("/reseau")
    public JSONAnswer RequestReseau() {
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList("select * from bi.dim_reseau"));
        System.out.println(a.getJsonAnswer());
        return a;
    }

    @GetMapping("/typecentrale")
    public JSONAnswer RequestTypeCentrale() {
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList("select * from bi.dim_type_centrale"));
        System.out.println(a.getJsonAnswer());
        return a;
    }


    @GetMapping("/typeevent")
    public JSONAnswer RequestTypeEvent() {
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList("select * from bi.dim_type_evenement "));
        System.out.println(a.getJsonAnswer());
        return a;
    }

    @GetMapping("/typeobjectif")
    public JSONAnswer RequestTypeObjectif() {
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList("select * from bi.dim_type_objectif "));
        System.out.println(a.getJsonAnswer());
        return a;
    }

    @GetMapping("/typesaisieobjectif")
    public JSONAnswer RequestTypeSaisieObjectif() {
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList("select * from bi.dim_type_saisieobjectif"));
        System.out.println(a.getJsonAnswer());
        return a;
    }

    @GetMapping("/{req}")
    public JSONAnswer Requests(@PathVariable("req") String req) {
        System.out.println(req);
        JSONAnswer a = new JSONAnswer();
        a.setJsonAnswer(jdbcTemplate.queryForList(req));
        System.out.println(a.getJsonAnswer());
        return a;
    }


}




/* EXEMPLE OF WEB CLIENT
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/

