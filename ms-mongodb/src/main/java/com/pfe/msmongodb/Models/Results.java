package com.pfe.msmongodb.Models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


@Document("results")
public class Results {

    @Id
    private String id;
    private long idresult;
    private List<String> JsonAnswer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getIdresult() {
        return idresult;
    }

    public void setIdresult(long idresult) {
        this.idresult = idresult;
    }

    public void setJsonAnswer(List<String> jsonAnswer) {
        JsonAnswer = jsonAnswer;
    }

    public List<String> getJsonAnswer() {
        return this.JsonAnswer;
    }

}
