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


public class StatCalculator{


    public static int gesamtpunkteCalc(@NonNull Spieler spieler){

        int result=0;
    int id = spieler.getId();
    for (SpielSpieler c : spieler.getStats()){
        if(c.getSpielerId() == id)
        {
            result += c.getPunkte();
            Log.d(TAG, "gesamtpunkteCalc: " + result);

        }
    }

        return result;
    }

    public static double punktePerSpielCalc(@NonNull Spieler spieler) {
        double result = 0;
        int punkte = 0;
        int attendendGames = 0;
        punkte = gesamtpunkteCalc(spieler);
        int id = spieler.getId();
        for (SpielSpieler c : spieler.getStats()) {
            if (c.getSpielerId() == id) {
                attendendGames++;
            }

        }
        result = punkte / attendendGames;
        return result;
    }
    public static String freiwurfquote(@NonNull Spieler spieler){
        double result = 0;
        double geworfen = 0;
        double getroffen = 0;
        int id = spieler.getId();
        for (SpielSpieler c : spieler.getStats()) {
            if (c.getSpielerId() == id) {
                Log.d(TAG, "freiwurfquote: Spieler gefunden ! \n Wert ist : "+ c.getGeworfeneFreiwuerfe() + "und " + c.getGetroffeneFreiwuerfe());
                geworfen += c.getGeworfeneFreiwuerfe();
                getroffen += c.getGetroffeneFreiwuerfe();
            }

        }

        result = getroffen/geworfen*100;
        double roundresult = Math.round(result*10.0)/10.0;
        Log.d(TAG, "freiwurfquote: nach berechnung : " + result);
        String Quote = String.valueOf(roundresult+"%");
        return Quote;
      //  return String.format(Locale.getDefault(),"%d %%",result);
    }


    public static String makeDateString(int dayOfMonth, int month, int year) {
        return dayOfMonth  + " " + getMonthFormat(month) + " " + year;
    }

    public static String getMonthFormat(int month) {
        if(month == 1)return "JAN";
        if(month == 2)return "FEB";
        if(month == 3)return "MÄR";
        if(month == 4)return "APR";
        if(month == 5)return "MAI";
        if(month == 6)return "JUN";
        if(month == 7)return "JUL";
        if(month == 8)return "AUG";
        if(month == 9)return "SEP";
        if(month == 10)return "OKT";
        if(month == 11)return "NOV";
        if(month == 12)return "DEZ";

        //default ---- shouldnt ever happen
        return "JAN";
    }
    public static String getTodaysDate(){
        Date currentdate = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy");
        String formattedDate = format.format(currentdate);

        return formattedDate;
    }
}
