package com.example.workloads.Phasen_Projekt;

import com.example.workloads.Phasen.Phasen;
import com.example.workloads.Softwareanforderungen.Softwareanforderungen;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Phasen_Projekt extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phasen_projekte_id;
    private int status;
    @ManyToOne
    @JoinColumn(name = "phasen_id_phasen_id")
    private Phasen phasen_id;
    @ManyToOne
    @JoinColumn(name = "softwareanforderungen_id_softwareanforderungen_id")
    private Softwareanforderungen softwareanforderungen_id;

    public Softwareanforderungen getSoftwareanforderungen_id() {
        return softwareanforderungen_id;
    }

    public void setSoftwareanforderungen_id(Softwareanforderungen softwareanforderungen_id) {
        this.softwareanforderungen_id = softwareanforderungen_id;
    }

    public Phasen getPhasen_id() {
        return phasen_id;
    }

    public void setPhasen_id(Phasen phasen_id) {
        this.phasen_id = phasen_id;
    }

    public Long getPhasen_projekte_id() {
        return phasen_projekte_id;
    }

    public void setPhasen_projekte_id(Long phasen_projekte_id) {
        this.phasen_projekte_id = phasen_projekte_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}