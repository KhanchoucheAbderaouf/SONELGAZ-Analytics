package com.pfe.loginpartjwt.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(generator="roles",strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "roles", sequenceName = "id_role",initialValue=1, allocationSize=1)
    private long idrole;
    @Column(length = 100,nullable = false)
    private String intitule;

    public long getIdrole() {
        return idrole;
    }

    public void setIdrole(long idrole) {
        this.idrole = idrole;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}

