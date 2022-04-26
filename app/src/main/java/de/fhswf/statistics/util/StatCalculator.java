package de.fhswf.statistics.util;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.list.viewholder.BaseViewHolder;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


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

    public static String freiwurfquoteCalc(@NonNull Spieler spieler) {
        double result;
        double geworfen;
        double getroffen;
        geworfen = geworfeneFreiwuerfeCalc(spieler);
        getroffen = getroffeneFreiwuerfeCalc(spieler);
        result = getroffen / geworfen * 100;
        double roundresult = Math.round(result * 10.0) / 10.0;

        return String.valueOf(roundresult + "%");
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

    public static int foulsCalc(@NonNull Spieler spieler) {
        int fouls = 0;
        int id = spieler.getId();
        for (SpielSpieler c : spieler.getStats()) {
            if (c.getSpielerId() == id) {
                fouls += c.getFouls();
            }
        }
        return fouls;
    }

    public static String makeDateString(int dayOfMonth, int month, int year) {
    //    return dayOfMonth + " " + getMonthFormat(month) + " " + year;
      //  return dayOfMonth + "-" + getMonthFormat(month) + "-" + year;
        return dayOfMonth + "." + month + "." + year;
    }

    public static String getMonthFormat(int month) {
        if (month == 1) return "JAN";
        if (month == 2) return "FEB";
        if (month == 3) return "MÄR";
        if (month == 4) return "APR";
        if (month == 5) return "MAI";
        if (month == 6) return "JUN";
        if (month == 7) return "JUL";
        if (month == 8) return "AUG";
        if (month == 9) return "SEP";
        if (month == 10) return "OKT";
        if (month == 11) return "NOV";
        if (month == 12) return "DEZ";

        //default ---- shouldnt ever happen
        return "JAN";
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
        Log.d(TAG, "getChosenString: " + stringlist);

        return stringlist;

    }
}
