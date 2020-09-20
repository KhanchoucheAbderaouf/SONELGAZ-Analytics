package com.pfe.springtest.model;

import java.util.List;
import java.util.Map;

public class JSONAnswer {
    private List<Map<String, Object>> JsonAnswer;


    public JSONAnswer(){

    }
    public JSONAnswer(List<Map<String, Object>> jsonAnswer) {
        JsonAnswer = jsonAnswer;
    }

    public List<Map<String, Object>> getJsonAnswer() {
        return JsonAnswer;
    }

    public void setJsonAnswer(List<Map<String, Object>> jsonAnswer) {
        JsonAnswer = jsonAnswer;
    }
}
