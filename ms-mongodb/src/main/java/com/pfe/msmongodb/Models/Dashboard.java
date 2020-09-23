package com.pfe.msmongodb.Models;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document("dashboard")
public class Dashboard {

    private String title;
    @JsonProperty("JsonAnswer")
    private List<Map<String, Object>> JsonAnswer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Map<String, Object>> getJsonAnswer() {
        return JsonAnswer;
    }

    public void setJsonAnswer(List<Map<String, Object>> jsonAnswer) {
        JsonAnswer = jsonAnswer;
    }
}
