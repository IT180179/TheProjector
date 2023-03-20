package com.example.workloads.Phasen;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Phasen extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phasen_id;
    private String titel;

    public Long getPhasen_id() {
        return phasen_id;
    }

    public void setPhasen_id(Long phasen_id) {
        this.phasen_id = phasen_id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
}