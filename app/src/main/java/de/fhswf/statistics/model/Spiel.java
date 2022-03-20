package de.fhswf.statistics.model;

import java.util.Date;

public class Spiel {

    private int id;

    private Date datum;

    private int HeimPunkte;

    private int GastPunkte;

    private String Teamname;

    public Spiel(int id, Date datum, int HeimPunkte, int GastPunkte,String Teamname) {
        this.id = id;
        this.datum = datum;
        this.HeimPunkte = HeimPunkte;
        this.GastPunkte = GastPunkte;
        this.Teamname = Teamname;
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
}
