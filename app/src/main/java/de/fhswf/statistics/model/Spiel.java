package de.fhswf.statistics.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Spiel {

    private int id;

    private Date datum;

    private int HeimPunkte;

    private int GastPunkte;

    private String Teamname;

    @Nullable
    private List<SpielSpieler> stats;

    public Spiel(int id, Date datum, int HeimPunkte, int GastPunkte, String Teamname) {
        this.id = id;
        this.datum = datum;
        this.HeimPunkte = HeimPunkte;
        this.GastPunkte = GastPunkte;
        this.Teamname = Teamname;
    }

    public Spiel(int id, Date datum) {
        this.id = id;
        this.datum = datum;
    }

    public Spiel() {

    }

    public Spiel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getHeimPunkte() {
        return HeimPunkte;
    }

    public void setHeimPunkte(int heimPunkte) {
        HeimPunkte = heimPunkte;
    }

    public int getGastPunkte() {
        return GastPunkte;
    }

    public void setGastPunkte(int gastPunkte) {
        GastPunkte = gastPunkte;
    }

    public String getTeamname() {
        return Teamname;
    }

    public void setTeamname(String teamname) {
        this.Teamname = teamname;
    }

    @Nullable
    public List<SpielSpieler> getStats() {
        return stats;
    }

    public void setStats(@Nullable List<SpielSpieler> stats) {
        this.stats = stats;
    }

    public Spiel addstats(@NonNull SpielSpieler stat) {
        if (this.stats == null)
            this.stats = new ArrayList<>();

        if (!stats.contains(stat))
            stats.add(stat);

        return this;
    }

}
