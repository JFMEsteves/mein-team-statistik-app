package de.fhswf.statistics.model;

import java.util.Objects;

/**
 * Datenmodell Spieldetails-Objekt
 */
public class Spieldetails {
    private int id;
    private int enemy;
    private int viertel1;
    private int viertel2;
    private int viertel3;
    private int viertel4;

    private Spiel spiel;

    public Spieldetails(int id, int enemy) {
        this.id = id;
        this.enemy = enemy;
    }

    public Spieldetails(int id, int enemy, int viertel1, int viertel2, int viertel3, int viertel4) {
        this.id = id;
        this.enemy = enemy;
        this.viertel1 = viertel1;
        this.viertel2 = viertel2;
        this.viertel3 = viertel3;
        this.viertel4 = viertel4;
    }

    public Spieldetails(int id, int enemy, int viertel1, int viertel2, int viertel3, int viertel4, Spiel spiel) {
        this.id = id;
        this.enemy = enemy;
        this.viertel1 = viertel1;
        this.viertel2 = viertel2;
        this.viertel3 = viertel3;
        this.viertel4 = viertel4;
        this.spiel = spiel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnemy() {
        return enemy;
    }

    public void setEnemy(int enemy) {
        this.enemy = enemy;
    }

    public int getViertel1() {
        return viertel1;
    }

    public void setViertel1(int viertel1) {
        this.viertel1 = viertel1;
    }

    public int getViertel2() {
        return viertel2;
    }

    public void setViertel2(int viertel2) {
        this.viertel2 = viertel2;
    }

    public int getViertel3() {
        return viertel3;
    }

    public void setViertel3(int viertel3) {
        this.viertel3 = viertel3;
    }

    public int getViertel4() {
        return viertel4;
    }

    public void setViertel4(int viertel4) {
        this.viertel4 = viertel4;
    }

    public Spiel getSpiel() {
        return spiel;
    }

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
    }

    @Override
    public String toString() {
        return "Spieldetails{" +
                "id=" + id +
                ", enemy=" + enemy +
                ", viertel1=" + viertel1 +
                ", viertel2=" + viertel2 +
                ", viertel3=" + viertel3 +
                ", viertel4=" + viertel4 +
                ", spiel=" + spiel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spieldetails that = (Spieldetails) o;

        if (id != that.id) return false;
        if (enemy != that.enemy) return false;
        if (viertel1 != that.viertel1) return false;
        if (viertel2 != that.viertel2) return false;
        if (viertel3 != that.viertel3) return false;
        if (viertel4 != that.viertel4) return false;
        return Objects.equals(spiel, that.spiel);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + enemy;
        result = 31 * result + viertel1;
        result = 31 * result + viertel2;
        result = 31 * result + viertel3;
        result = 31 * result + viertel4;
        result = 31 * result + (spiel != null ? spiel.hashCode() : 0);
        return result;
    }
}
