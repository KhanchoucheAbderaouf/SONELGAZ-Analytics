package com.pfe.loginpartjwt.models;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(generator="users",strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "users", sequenceName = "id_user",initialValue=1, allocationSize=1)
    private long iduser;
    @Column(length=255,unique = true,nullable = false)
    private String username;
    @Column(length=255)
    private String password;
    private boolean active;
    @Column(length=50,nullable = false)
    private String nom;
    @Column(length=50,nullable = false)
    private String prenom;
    @Column(length=10)
    private String telephone;
    @Column(length=100)
    private String email;
    private long idorganism;
    private Date date_creation;
    @OneToOne
    @JoinColumn(name="idrole")
    private Roles role;

    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable
            (
                    name="table_ref",
                    joinColumns={ @JoinColumn(name="iduser", referencedColumnName="iduser") },
                    inverseJoinColumns={ @JoinColumn(name="idquery", referencedColumnName="idquery") }
            )
    private List<Queries> listQueries;

    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable
            (
                    name="table_ref_user_rapport",
                    joinColumns={ @JoinColumn(name="iduser", referencedColumnName="iduser") },
                    inverseJoinColumns={@JoinColumn(name="idrapport", referencedColumnName="idrapport" ) }
            )
    private List<Rapports> listRapports;

    public List<Rapports> getListRapports() {
        return listRapports;
    }

    public void setListRapports(Rapports rapport) {
        this.listRapports.add(rapport);
    }

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

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Queries> getListQueries() {
        return listQueries;
    }

    public void setListQueries(Queries q) {
        this.listQueries.add(q);
    }

}
