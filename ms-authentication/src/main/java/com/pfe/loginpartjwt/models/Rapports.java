package com.pfe.loginpartjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "rapports")
public class Rapports {

    @Id
    @GeneratedValue(generator="rapports",strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "rapports", sequenceName = "id_rapports",initialValue=1, allocationSize=1)
    private long idrapport;
    @Column(length = 100,nullable = false,unique=true)
    private String titre;
    @Column(length = 255,nullable = false)
    private String creator;


    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable
            (
                    name="table_ref_rapport_query",
                    joinColumns={ @JoinColumn(name="idrapport", referencedColumnName="idrapport") },
                    inverseJoinColumns={ @JoinColumn(name="idquery", referencedColumnName="idquery") }
            )
    private List<Queries> listQueries;


    public long getIdrapport() {
        return idrapport;
    }

    public void setIdrapport(long idrapport) {
        this.idrapport = idrapport;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Queries> getListQueries() {
        return listQueries;
    }


    public void setListQueries(Queries query) {
        this.listQueries.add(query);
    }



    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

}
