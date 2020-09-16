package com.pfe.msmongodb.Models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


@Document("results")
public class Results {

    @Id
    private String id;
    private String title;
    private List<String> JsonAnswer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setJsonAnswer(List<String> jsonAnswer) {
        JsonAnswer = jsonAnswer;
    }

    public List<String> getJsonAnswer() {
        return this.JsonAnswer;
    }

}
