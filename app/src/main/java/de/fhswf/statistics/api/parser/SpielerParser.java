package de.fhswf.statistics.api.parser;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

/**
 * Parser für eine einzelnen Spieler.
 *
 * @see StatParser Stats werden mit einem anderen Parser verarbeitet.
 */
public class SpielerParser implements ResponseParser<Spieler> {

    @NotNull
    @Override
    public Spieler parse(@NotNull JSONObject data) throws ParsingException {
        //init Spieler Object mit unique Data
        Spieler spieler = new Spieler(data.optInt("id"),
                data.optString("name", ""));


        // Stats sind optional
        if (data.has("stats")) {
            try {
                JSONArray array = data.getJSONArray("stats");

                ArrayList<SpielSpieler> stats = new ArrayList<>();
                StatParser parser = new StatParser();

                for (int i = 0; i < array.length(); i++) {
                    stats.add(parser.parse(array.getJSONObject(i)));
                }

                spieler.setStats(stats);

            } catch (JSONException e) {
                throw new ParsingException("Unable to read statistics.", e);
            }
        }

        return spieler;
    }
}
