package de.fhswf.statistics.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.Spieler;

/**
 * Sortiert die Recyclerview - Inhalte nach den gewählten Kriterien
 *
 * @author Joey Esteves
 * @version 1.0
 */
public class ViewSort {

    public static HashMap<String, Double> createHashMapSpieler(List<Spieler> spielerList, String choice) {
        HashMap<String, Double> map = new HashMap<>();
        switch (choice) {
            case "Gesamtpunkte":
                for (Spieler c : spielerList) {
                    map.put(c.getName(), (double) StatCalculator.gesamtpunkteCalc(c));
                }
                break;
            case "PunkteProSpiel":
                for (Spieler c : spielerList) {
                    map.put(c.getName(), StatCalculator.punktePerSpielCalc(c));
                }
                break;
            case "Freiwurfquote":
                for (Spieler c : spielerList) {
                    map.put(c.getName(), Double.valueOf(StatCalculator.freiwurfquoteCalc(c, false)));
                }
                break;
            case "Fouls":
                for (Spieler c : spielerList) {
                    map.put(c.getName(), (double) StatCalculator.foulsCalcSpieler(c));
                }
                break;
            default:
                break;
        }
        return map;
    }

    public static HashMap<String, Double> createHashMapSpiel(List<Spiel> spielList, String choice) {
        HashMap<String, Double> map = new HashMap<>();
        switch (choice) {
            case "Punktedifferenz":
                for (Spiel c : spielList) {
                    map.put(c.getTeamname(), (double) StatCalculator.punktedifferenzCalc(c));
                }
                break;
            case "Teamfouls":
                for (Spiel c : spielList) {
                    map.put(c.getTeamname(), (double) StatCalculator.foulsCalcSpiel(c));
                }
                break;
        }
        return map;
    }

    public static ArrayList<String> sortedList(HashMap<String, Double> map, boolean reverse) {
        ArrayList<Double> list = new ArrayList<>();
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        // sortiere die Werte in der Liste
        Collections.sort(list);
        if (reverse) Collections.reverse(list);
        // sortiere die Map nach den  sortierten Werten der Liste
        for (Double num : list) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }

        }
        Set<String> keys = sortedMap.keySet();
        return new ArrayList<>(keys);
    }
}
