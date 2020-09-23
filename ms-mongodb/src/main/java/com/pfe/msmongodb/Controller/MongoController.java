package com.pfe.msmongodb.Controller;


import com.pfe.msmongodb.Models.JSONAnswer;
import com.pfe.msmongodb.Models.Results;
import com.pfe.msmongodb.Repositories.ResultsRepository;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;



@RestController
@CrossOrigin
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    ResultsRepository resultsRepository;






    @PostMapping("/addResult")
    public void insert(@RequestBody Results result) throws JSONException, ParseException {

        result.setCreationDate(new Date());
        resultsRepository.save(result);
    }


    @GetMapping("/getOneResult/{title}")
    public JSONAnswer oneResult(@PathVariable("title") String title) throws Exception {
        if (!(resultsRepository.findByTitle(title).isPresent())){
            throw new Exception("not found");
        }else {
             JSONAnswer jsonAnswer = new JSONAnswer(resultsRepository.findByTitle(title).get().getJsonAnswer());
            return jsonAnswer;
        }
    }


}