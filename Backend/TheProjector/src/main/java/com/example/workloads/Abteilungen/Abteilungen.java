package com.example.workloads.Abteilungen;


import com.example.workloads.Bereiche.Bereiche;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Abteilungen extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long abteilungs_id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "bereichs_id_bereich_id")
    private Bereiche bereichs_id;

    public Long getAbteilungs_id() {
        return abteilungs_id;
    }

    public void setAbteilungs_id(Long abteilungs_id) {
        this.abteilungs_id = abteilungs_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bereiche getBereichs_id() {
        return bereichs_id;
    }

    public void setBereichs_id(Bereiche bereichs_id) {
        this.bereichs_id = bereichs_id;
    }
}