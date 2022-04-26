package de.fhswf.statistics.api.parser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;


import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.util.DateConverter;


import java.util.Date;
import java.util.ArrayList;

public class SpielParser implements ResponseParser<Spiel>{
    @NotNull
    @Override
    public Spiel parse(@NotNull JSONObject data) throws ParsingException{
        try{
        //TODO FIX DATE Is it fixed ?
        Spiel spiel = new Spiel(data.optInt("id"), DateConverter.StringtoDate(data.optString("datum")));
        spiel.setGastPunkte(data.optInt("gegenerPunkte"));
        spiel.setHeimPunkte(data.optInt("unserePunkte"));

        //TODO DUPLICATED CODE auslagern
        JSONArray array = data.getJSONArray("stats");

        ArrayList<SpielSpieler> stats = new ArrayList<>();
        StatParser parser = new StatParser();

        for (int i = 0; i < array.length(); i++) {
            stats.add(parser.parse(array.getJSONObject(i)));
        }

        spiel.setStats(stats);



        return spiel;
        } catch (Exception e) {
            throw new ParsingException("Parsing Spiel failed.", e);
        }
    }
}
