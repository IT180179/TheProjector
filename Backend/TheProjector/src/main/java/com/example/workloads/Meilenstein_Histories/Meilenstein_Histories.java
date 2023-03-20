package com.example.workloads.Meilenstein_Histories;

import com.example.workloads.Meilensteine.Meilensteine;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Meilenstein_Histories extends PanacheEntityBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long meilenstein_histories_id;

    @ManyToOne
    @JoinColumn(name = "meilenstein_id_meilensteine_id")
    private Meilensteine meilenstein_id;
    private String aenderung;
    private int alter_status;
    private LocalDate datum;
    private LocalDate end_datum;

    public Long getMeilenstein_histories_id() {
        return meilenstein_histories_id;
    }

    public void setMeilenstein_histories_id(Long meilenstein_histories_id) {
        this.meilenstein_histories_id = meilenstein_histories_id;
    }

    public Meilensteine getMeilenstein_id() {
        return meilenstein_id;
    }

    public void setMeilenstein_id(Meilensteine meilenstein_id) {
        this.meilenstein_id = meilenstein_id;
    }

    public String getAenderung() {
        return aenderung;
    }

    public void setAenderung(String aenderung) {
        this.aenderung = aenderung;
    }

    public int getAlter_status() {
        return alter_status;
    }

    public void setAlter_status(int alter_status) {
        this.alter_status = alter_status;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalDate getEnd_datum() {
        return end_datum;
    }

    public void setEnd_datum(LocalDate end_datum) {
        this.end_datum = end_datum;
    }
}