package com.pfe.loginpartjwt.models;


import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;
import java.util.Map;


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
