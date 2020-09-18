package com.pfe.msmongodb.Models;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Document("results")
public class Results {

    private String title;
    @JsonProperty("JsonAnswer")
    private List<Map<String, Object>> JsonAnswer;
    @CreatedDate
    private Date creationDate;


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

    public List<Map<String, Object>> getJsonAnswer() {
        return JsonAnswer;
    }

    public void setJsonAnswer(List<Map<String, Object>> jsonAnswer) {
        JsonAnswer = jsonAnswer;
    }
}

