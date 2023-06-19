package de.fhswf.statistics.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Datenmodell Spiel-Objekt
 */
public class Spiel {

    private int id;

    private Date datum;

    private int HeimPunkte;

    private int GastPunkte;

    private int erstesViertelTeam;

    private int zweitesViertelTeam;

    private int drittesViertelTeam;

    private int viertesViertelTeam;

    private int erstesViertelGegner;

    private int zweitesViertelGegner;

    private int drittesViertelGegner;

    private int viertesViertelGegner;

    private String Teamname;

    @Nullable
    private List<SpielSpieler> stats;

    public Spiel(int id, Date datum, int heimPunkte, int gastPunkte, int erstesViertelTeam, int zweitesViertelTeam, int drittesViertelTeam, int viertesViertelTeam, int erstesViertelGegner, int zweitesViertelGegner, int drittesViertelGegner, int viertesViertelGegner, String teamname) {
        this.id = id;
        this.datum = datum;
        this.HeimPunkte = heimPunkte;
        this.GastPunkte = gastPunkte;
        this.erstesViertelTeam = erstesViertelTeam;
        this.zweitesViertelTeam = zweitesViertelTeam;
        this.drittesViertelTeam = drittesViertelTeam;
        this.viertesViertelTeam = viertesViertelTeam;
        this.erstesViertelGegner = erstesViertelGegner;
        this.zweitesViertelGegner = zweitesViertelGegner;
        this.drittesViertelGegner = drittesViertelGegner;
        this.viertesViertelGegner = viertesViertelGegner;
        this.Teamname = teamname;
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

    public int getErstesViertelTeam() {
        return erstesViertelTeam;
    }

    public void setErstesViertelTeam(int erstesViertelTeam) {
        this.erstesViertelTeam = erstesViertelTeam;
    }

    public int getZweitesViertelTeam() {
        return zweitesViertelTeam;
    }

    public void setZweitesViertelTeam(int zweitesViertelTeam) {
        this.zweitesViertelTeam = zweitesViertelTeam;
    }

    public int getDrittesViertelTeam() {
        return drittesViertelTeam;
    }

    public void setDrittesViertelTeam(int drittesViertelTeam) {
        this.drittesViertelTeam = drittesViertelTeam;
    }

    public int getViertesViertelTeam() {
        return viertesViertelTeam;
    }

    public void setViertesViertelTeam(int viertesViertelTeam) {
        this.viertesViertelTeam = viertesViertelTeam;
    }

    public int getErstesViertelGegner() {
        return erstesViertelGegner;
    }

    public void setErstesViertelGegner(int erstesViertelGegner) {
        this.erstesViertelGegner = erstesViertelGegner;
    }

    public int getZweitesViertelGegner() {
        return zweitesViertelGegner;
    }

    public void setZweitesViertelGegner(int zweitesViertelGegner) {
        this.zweitesViertelGegner = zweitesViertelGegner;
    }

    public int getDrittesViertelGegner() {
        return drittesViertelGegner;
    }

    public void setDrittesViertelGegner(int drittesViertelGegner) {
        this.drittesViertelGegner = drittesViertelGegner;
    }

    public int getViertesViertelGegner() {
        return viertesViertelGegner;
    }

    public void setViertesViertelGegner(int viertesViertelGegner) {
        this.viertesViertelGegner = viertesViertelGegner;
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
