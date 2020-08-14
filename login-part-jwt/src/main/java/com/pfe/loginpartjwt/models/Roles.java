package com.pfe.loginpartjwt.models;

import javax.persistence.*;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Idrole;
    @Column(length=50)
    private String Intitule;

    public long getIdrole() {
        return Idrole;
    }

    public void setIdrole(long idrole) {
        Idrole = idrole;
    }

    public String getIntitule() {
        return Intitule;
    }

    public void setIntitule(String intitule) {
        Intitule = intitule;
    }
}

