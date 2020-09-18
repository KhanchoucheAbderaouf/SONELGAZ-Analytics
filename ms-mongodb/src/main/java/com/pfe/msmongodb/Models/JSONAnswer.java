package com.pfe.msmongodb.Models;

import java.util.List;
import java.util.Map;

public class JSONAnswer {
    private List<Map<String, Object>> JsonAnswer;


    public JSONAnswer(){

    }
    public JSONAnswer(List<Map<String, Object>> jsonAnswer) {

        this.JsonAnswer = jsonAnswer;
    }

    public List<Map<String, Object>> getJsonAnswer() {
        return this.JsonAnswer;
    }

    public void setJsonAnswer(List<Map<String, Object>> jsonAnswer) {
        this.JsonAnswer = jsonAnswer;
    }
    public void setJsonAnswer(Map<String, Object> jsonAnswer) {
        this.JsonAnswer.add(jsonAnswer);}


}
