package com.example.workloads.PPK_Projekte;

import com.example.workloads.PPK.PPK;
import com.example.workloads.Projekte.Projekte;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PPK_ProjekteId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "ppk_id_ppk_id")
    private PPK ppk_id;

    @ManyToOne
    @JoinColumn(name = "projekte_id_projekt_id")
    private Projekte projekte_id;

    public Projekte getProjekte_id() {
        return projekte_id;
    }

    public void setProjekte_id(Projekte projekte_id) {
        this.projekte_id = projekte_id;
    }

    public PPK getPpk_id() {
        return ppk_id;
    }

    public void setPpk_id(PPK ppk_id) {
        this.ppk_id = ppk_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PPK_ProjekteId)) return false;
        PPK_ProjekteId that = (PPK_ProjekteId) o;
        return Objects.equals(getPpk_id(), that.getPpk_id()) && Objects.equals(getProjekte_id(), that.getProjekte_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPpk_id(), getProjekte_id());
    }
}