package de.fhswf.statistics.model;

import java.util.Objects;

/**
 * Datenmodell SpielSpieler-Objekt
 */
public class SpielSpieler {

    private int SpielerId;

    private int SpielId;

    private int punkte;

    private int geworfeneFreiwuerfe;

    private int getroffeneFreiwuerfe;

    private int dreiPunkteTreffer;

    private int Fouls;

    public SpielSpieler(int spielerId, int spielId, int punkte, int geworfeneFreiwuerfe, int getroffeneFreiwuerfe, int dreiPunkteTreffer, int Fouls) {
        this.SpielerId = spielerId;
        this.SpielId = spielId;
        this.punkte = punkte;
        this.geworfeneFreiwuerfe = geworfeneFreiwuerfe;
        this.getroffeneFreiwuerfe = getroffeneFreiwuerfe;
        this.dreiPunkteTreffer = dreiPunkteTreffer;
        this.Fouls = Fouls;
    }

    public SpielSpieler(int spielerId, int spielId) {
        this.SpielerId = spielerId;
        this.SpielId = spielId;
    }

    public SpielSpieler() {
    }

    public int getSpielerId() {
        return SpielerId;
    }

    public void setSpielerId(int spielerId) {
        SpielerId = spielerId;
    }

    public int getSpielId() {
        return SpielId;
    }

    public void setSpielId(int spielId) {
        SpielId = spielId;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public int getGeworfeneFreiwuerfe() {
        return geworfeneFreiwuerfe;
    }

    public void setGeworfeneFreiwuerfe(int geworfeneFreiwuerfe) {
        this.geworfeneFreiwuerfe = geworfeneFreiwuerfe;
    }

    public int getGetroffeneFreiwuerfe() {
        return getroffeneFreiwuerfe;
    }

    public void setGetroffeneFreiwuerfe(int getroffeneFreiwuerfe) {
        this.getroffeneFreiwuerfe = getroffeneFreiwuerfe;
    }

    public int getDreiPunkteTreffer() {
        return dreiPunkteTreffer;
    }

    public void setDreiPunkteTreffer(int dreiPunkteTreffer) {
        this.dreiPunkteTreffer = dreiPunkteTreffer;
    }

    public int getFouls() {
        return Fouls;
    }

    public void setFouls(int fouls) {
        Fouls = fouls;
    }


    @Override
    public int hashCode() {
        return Objects.hash(SpielerId, SpielId, geworfeneFreiwuerfe, getroffeneFreiwuerfe, dreiPunkteTreffer);
    }

    @Override
    public String toString() {
        return "SpielSpieler{" +
                "SpielerId=" + SpielerId +
                ", SpielId=" + SpielId +
                ", punkte=" + punkte +
                ", geworfeneFreiwuerfe=" + geworfeneFreiwuerfe +
                ", getroffeneFreiwuerfe=" + getroffeneFreiwuerfe +
                ", dreiPunkteTreffer=" + dreiPunkteTreffer +
                ", Fouls=" + Fouls +
                '}';
    }
}
