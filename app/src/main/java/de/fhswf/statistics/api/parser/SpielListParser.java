package de.fhswf.statistics.api.parser;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.fhswf.statistics.model.Spiel;

/**
 * Implementierung des ResponseParser Interfaces für Listen von Spiel-Objekten
 *
 * @see ResponseParser
 */
public class SpielListParser implements ResponseParser<List<Spiel>> {

    @NonNull
    @Override
    public List<Spiel> parse(@NonNull JSONObject data) throws ParsingException {
        try {
            JSONArray array = data.getJSONArray("spiel");

            ArrayList<Spiel> kader = new ArrayList<>();
            SpielParser parser = new SpielParser();

            for (int i = 0; i < array.length(); i++) {
                kader.add(parser.parse(array.getJSONObject(i)));
            }
            return kader;
        } catch (JSONException e) {
            // Dieser Fall sollte nur auftreten, wenn das Feld für die Liste von Surveys nicht
            // existiert oder falsche Inhalte hat. Die ursprüngliche Exception wird in einer
            // ParsingException gekapselt.
            throw new ParsingException("Reading list of spiel failed.", e);
        }
    }
}