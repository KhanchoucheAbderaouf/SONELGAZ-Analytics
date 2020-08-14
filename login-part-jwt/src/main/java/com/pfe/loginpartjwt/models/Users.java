package com.pfe.loginpartjwt.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long iduser;
    @Column(length=255)
    private String username;
    @Column(length=255)
    private String password;
    private boolean active;
    @Column(length=50)
    private String nom;
    @Column(length=50)
    private String prenom;
    @Column(length=20)
    private String telephone;
    @Column(length=100)
    private String email;
    private long idorganism;
    @OneToOne
    private Roles role;
    private Date date_creation;

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIdorganism() {
        return idorganism;
    }

    public void setIdorganism(long idorganism) {
        this.idorganism = idorganism;
    }

    public Roles getRole() {
        return role;
    }

    public void Roles(Roles role) {
        this.role = role;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation() {
        this.date_creation = new Date();
    }
}
