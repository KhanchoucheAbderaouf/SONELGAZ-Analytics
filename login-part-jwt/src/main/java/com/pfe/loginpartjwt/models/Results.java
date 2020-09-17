package com.pfe.loginpartjwt.models;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;



public class Results {

    @Id
    private String id;
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationDate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setJsonAnswer(List<String> jsonAnswer) {
        JsonAnswer = jsonAnswer;
    }

    public List<String> getJsonAnswer() {
        return this.JsonAnswer;
    }

}
