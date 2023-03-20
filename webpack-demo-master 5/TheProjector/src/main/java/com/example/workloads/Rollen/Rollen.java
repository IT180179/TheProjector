package com.example.workloads.Rollen;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rollen extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long rollen_id;
    private String name;

    public Long getRollen_id() {
        return rollen_id;
    }

    public void setRollen_id(Long rollen_id) {
        this.rollen_id = rollen_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}