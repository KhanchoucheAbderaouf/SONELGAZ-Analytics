package com.pfe.msmongodb.Controller;


import com.pfe.msmongodb.Models.Results;
import com.pfe.msmongodb.Repositories.ResultsRepository;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    ResultsRepository resultsRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/allInfo")
    public String allInfo() throws JSONException {

        List<Map<String,Object>> results = null;
        List<Results> clients =mongoTemplate.findAll(Results.class);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < clients.get(0).getJsonAnswer().size(); i++) {
            jsonObject = new JSONObject(clients.get(0).getJsonAnswer().get(i));
            jsonArray.put(jsonObject);
        }

        System.out.println(jsonArray.toString());
        return jsonArray.toString();
    }


    @PostMapping("/addResult")
    public void insert(@RequestBody LinkedHashMap result) throws JSONException {
        System.out.println(result);
        JSONObject jsonObject = new JSONObject(result.toString());
        System.out.println(jsonObject);
        mongoTemplate.insert(jsonObject.toString(),"results");
    }


    @GetMapping("/getOneResult/{idresult}")
    public String oneResult(@PathVariable("idresult") long id) throws JSONException {

        Results client =mongoTemplate.findOne(Query.query(Criteria.where("idresult").is(id)), Results.class,"results");
        System.out.println(client.getJsonAnswer());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < client.getJsonAnswer().size(); i++) {
            jsonObject = new JSONObject(client.getJsonAnswer().get(i));
            jsonArray.put(jsonObject);
        }

        System.out.println(jsonArray.toString());
        return jsonArray.toString();
    }

    public JSONArray listmap_to_json_string(List<Map<String, Object>> list)
    {
        JSONArray jsonArray=new JSONArray();
        for (Map<String, Object> map : list) {
            JSONObject jsonObject=new JSONObject();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                try {
                    jsonObject.put(key,value);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }


}/*
[
        {
        "nom_unite": "Unité Cycle Combiné d'Ain Arnat",
        "sum_densite_fuel": 0.0,
        "sum_stock_fuel_initial": 0.0
        },
        {
        "nom_unite": "unite Cycle Combiné de Naama",
        "sum_densite_fuel": 0.0,
        "sum_stock_fuel_initial": 0.0
        },
        {
        "nom_unite": "Unité Cycle Combiné de Ras Djinet",
        "sum_densite_fuel": 0.0,
        "sum_stock_fuel_initial": 0.0
        },
        {
        "nom_unite": "Unité Turbine à Gaz d'Adrar",
        "sum_densite_fuel": 52005.595999939826,
        "sum_stock_fuel_initial": 1.1546217824498242E8
        },
        {
        "nom_unite": "Unité Turbine à Gaz d'Annaba",
        "sum_densite_fuel": 5507.868800000142,
        "sum_stock_fuel_initial": 2.70293175678062E7
        }
        ]*/