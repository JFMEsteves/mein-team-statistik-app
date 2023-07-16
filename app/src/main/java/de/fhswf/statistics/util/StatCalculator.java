package de.fhswf.statistics.util;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

/**
 * Enthält alle mathematischen Formeln zur Auswertung der Werte
 */
public final class StatCalculator {

    private StatCalculator() {
    }


    public static int gesamtpunkteCalc(@NonNull Spieler spieler) {

        int result = 0;
        if (spieler.getStats() != null) {
            int id = spieler.getId();
            for (SpielSpieler c : spieler.getStats()) {
                if (c.getSpielerId() == id) {
                    result += c.getPunkte();

                }
            }
        }

        return result;
    }

    public static int attendedGamesCalc(@NonNull Spieler spieler) {
        int attendedGames = 0;

        if (spieler.getStats() != null) {
            int id = spieler.getId();
            for (SpielSpieler c : spieler.getStats()) {
                if (c.getSpielerId() == id) attendedGames++;
            }
        }
        return attendedGames;
    }

    public static double punktePerSpielCalc(@NonNull Spieler spieler) {


        int punkte = gesamtpunkteCalc(spieler);
        int attendedGames = attendedGamesCalc(spieler);
        if (punkte == 0 && attendedGames == 0) return 0;
        return punkte / attendedGames;
    }

    public static int dreierCalc(@NonNull Spieler spieler) {
        int dreier = 0;
        int id = spieler.getId();
        for (SpielSpieler c : spieler.getStats()) {
            if (c.getSpielerId() == id) {
                dreier += c.getDreiPunkteTreffer();
            }
        }
        return dreier;
    }

    public static String freiwurfquoteCalc(@NonNull Spieler spieler, boolean isString) {
        double result;
        double geworfen;
        double getroffen;
        geworfen = geworfeneFreiwuerfeCalc(spieler);
        getroffen = getroffeneFreiwuerfeCalc(spieler);
        if (geworfen == 0 && isString) {
            return 0 + "%";
        }
        result = getroffen / geworfen * 100;
        double roundresult = Math.round(result * 10.0) / 10.0;

        if (isString) {
            return roundresult + "%";
        }
        return String.valueOf(roundresult);
    }

    public static int geworfeneFreiwuerfeCalc(@NonNull Spieler spieler) {
        int geworfen = 0;
        int id = spieler.getId();
        for (SpielSpieler c : spieler.getStats()) {
            if (c.getSpielerId() == id) {
                geworfen += c.getGeworfeneFreiwuerfe();
            }
        }
        return geworfen;
    }

    public static int getroffeneFreiwuerfeCalc(@NonNull Spieler spieler) {
        int getroffen = 0;
        int id = spieler.getId();
        for (SpielSpieler c : spieler.getStats()) {
            if (c.getSpielerId() == id) {
                getroffen += c.getGetroffeneFreiwuerfe();
            }
        }
        return getroffen;
    }

    public static int foulsCalcSpieler(@NonNull Spieler spieler) {
        int fouls = 0;
        int id = spieler.getId();
        for (SpielSpieler c : spieler.getStats()) {
            if (c.getSpielerId() == id) {
                fouls += c.getFouls();
            }
        }
        return fouls;
    }

    public static int foulsCalcSpiel(@NonNull Spiel spiel) {
        int fouls = 0;
        int id = spiel.getId();
        for (SpielSpieler c : spiel.getStats()) {
            if (c.getSpielId() == id) {
                fouls += c.getFouls();
            }
        }
        return fouls;
    }


    public static String makeDateString(int dayOfMonth, int month, int year) {
        return dayOfMonth + "." + month + "." + year;
    }


    public static String getTodaysDate() {
        Date currentdate = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy");

        return format.format(currentdate);
    }

    public static String getChosenString(ArrayList<Spieler> list) {
        String stringlist = "";
        for (Spieler c : list) {
            stringlist.concat(c.getId() + ";");
        }
        return stringlist;

    }
}
