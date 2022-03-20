package de.fhswf.statistics.util;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.list.viewholder.BaseViewHolder;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


public class StatCalculator{


    public static int gesamtpunkteCalc(Spieler spieler){

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

    public static double punktePerSpielCalc(Spieler spieler) {
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



    public int gesamtpunkteCalcItem(SpielerListItem item){
        Log.d(TAG, "gesamtpunkteCalc: Anfang");
        int result=0;
        int id = item.getSpieler().getId();
        Log.d(TAG, "gesamtpunkteCalc: Nach getID");
        for (SpielSpieler c : item.getSpieler().getStats()){
            Log.d(TAG, "gesamtpunkteCalc: Innerhalb For schleife");
            if(Objects.equals(id, c.getSpielerId()))
            {
                result += c.getPunkte();
                Log.d(TAG, "gesamtpunkteCalc: " + result);
                //TODO weitermachen siehe MockSurveyService zeile 75
            }
        }

        return result;
    }
}
