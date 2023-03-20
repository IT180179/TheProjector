package com.example.workloads.Einsaetze;

import com.example.workloads.Personen.Personen;
import com.example.workloads.Projekte.Projekte;
import com.example.workloads.Rollen.Rollen;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EinsaetzeId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "personen_id_personen_id")
    private Personen personen_id;

    @ManyToOne
    @JoinColumn(name = "projekte_id_projekt_id")
    private Projekte projekte_id;

    @ManyToOne
    @JoinColumn(name = "rollen_id_rollen_id")
    private Rollen rollen_id;

    public Rollen getRollen_id() {
        return rollen_id;
    }

    public void setRollen_id(Rollen rollen_id) {
        this.rollen_id = rollen_id;
    }

    public Projekte getProjekte_id() {
        return projekte_id;
    }

    public void setProjekte_id(Projekte projekte_id) {
        this.projekte_id = projekte_id;
    }

    public Personen getPersonen_id() {
        return personen_id;
    }

    public void setPersonen_id(Personen personen_id) {
        this.personen_id = personen_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EinsaetzeId)) return false;
        EinsaetzeId that = (EinsaetzeId) o;
        return Objects.equals(getPersonen_id(), that.getPersonen_id()) && Objects.equals(getProjekte_id(), that.getProjekte_id()) && Objects.equals(getRollen_id(), that.getRollen_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonen_id(), getProjekte_id(), getRollen_id());
    }
}