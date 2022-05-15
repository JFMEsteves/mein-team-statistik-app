package de.fhswf.statistics.api.parser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.util.DateConverter;

/**
 * Parser für einzelne Fragen.
 */
public class SpielParser implements ResponseParser<Spiel> {
    @NotNull
    @Override
    public Spiel parse(@NotNull JSONObject data) throws ParsingException {
        try {
            Spiel spiel = new Spiel(data.optInt("id"), DateConverter.StringtoDate(data.optString("datum")));
            spiel.setGastPunkte(data.optInt("gegnerPunkte"));
            spiel.setHeimPunkte(data.optInt("unserePunkte"));
            spiel.setTeamname(data.optString("name"));
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
