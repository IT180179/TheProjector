package com.example.workloads.Gaeste;

import com.example.workloads.PPK.PPK;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Gaeste extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long gaeste_id;

    @ManyToOne
    @JoinColumn(name = "ppk_id_ppk_id")
    private PPK ppk_id;
    private String name;

    public Long getGaeste_id() {
        return gaeste_id;
    }

    public void setGaeste_id(Long gaeste_id) {
        this.gaeste_id = gaeste_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PPK getPpk_id() {
        return ppk_id;
    }

    public void setPpk_id(PPK ppk_id) {
        this.ppk_id = ppk_id;
    }
}