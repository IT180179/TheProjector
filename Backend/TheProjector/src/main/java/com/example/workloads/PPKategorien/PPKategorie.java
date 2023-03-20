package com.example.workloads.PPKategorien;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PPKategorie extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long ppKategorie;
    private String name;

    public Long getPpKategorie() {
        return ppKategorie;
    }

    public void setPpKategorie(Long ppKategorie) {
        this.ppKategorie = ppKategorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}