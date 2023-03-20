package com.example.workloads.Kategorien;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kategorien extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long kategorien_id;
    private String name;

    public Long getKategorien_id() {
        return kategorien_id;
    }

    public void setKategorien_id(Long kategorien_id) {
        this.kategorien_id = kategorien_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}