package com.example.workloads.Ressourcen;

import com.example.workloads.PPK_Projekte.PPK_Projekte;
import com.example.workloads.Personen.Personen;
import com.example.workloads.Projekte.Projekte;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RessourcenID implements Serializable {
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PPK_PROJEKTE_ID_PPK_ID_PPK_ID", referencedColumnName = "PPK_ID_PPK_ID"),
            @JoinColumn(name = "PPK_PROJEKTE_ID_PROJEKTE_ID_PROJEKT_ID", referencedColumnName = "PROJEKTE_ID_PROJEKT_ID")
    })
    private PPK_Projekte ppk_projekte_id;

    @ManyToOne
    @JoinColumn(name = "personen_id_personen_id")
    private Personen personen_id;

    public Personen getPersonen_id() {
        return personen_id;
    }

    public void setPersonen_id(Personen personen_id) {
        this.personen_id = personen_id;
    }

    public PPK_Projekte getPpk_projekte_id() {
        return ppk_projekte_id;
    }

    public void setPpk_projekte_id(PPK_Projekte ppk_projekte_id) {
        this.ppk_projekte_id = ppk_projekte_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RessourcenID)) return false;
        RessourcenID that = (RessourcenID) o;
        return Objects.equals(getPpk_projekte_id(), that.getPpk_projekte_id()) && Objects.equals(getPersonen_id(), that.getPersonen_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPpk_projekte_id(), getPersonen_id());
    }
}