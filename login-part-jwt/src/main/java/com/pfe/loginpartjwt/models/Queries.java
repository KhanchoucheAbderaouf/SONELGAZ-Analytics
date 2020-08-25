package com.pfe.loginpartjwt.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "queries")
public class Queries {

    @Id
    @GeneratedValue(generator="queries",strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "queries", sequenceName = "id_query",initialValue=1, allocationSize=1)
    private long idquery;
    private String titre;
    private String valeur;
    private Date date_creation;
    private String creator;

    @ManyToMany
    List<Users> users;

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public long getIdquery() {
        return idquery;
    }
    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public void setIdquery(long idquery) {
        this.idquery = idquery;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }



    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }






}
