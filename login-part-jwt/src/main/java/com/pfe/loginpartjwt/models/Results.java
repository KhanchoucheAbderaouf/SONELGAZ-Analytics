package com.pfe.loginpartjwt.models;


import org.springframework.data.annotation.Id;


import java.util.List;



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
