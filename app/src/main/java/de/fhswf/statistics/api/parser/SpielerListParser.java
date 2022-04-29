package de.fhswf.statistics.api.parser;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.fhswf.statistics.model.Spieler;

public class SpielerListParser
        implements ResponseParser<List<Spieler>> {

    @NonNull
    @Override
    public List<Spieler> parse(@NonNull JSONObject data) throws ParsingException {
        try{
            JSONArray array = data.getJSONArray("kader");

            ArrayList<Spieler> kader = new ArrayList<>();
            SpielerParser parser = new SpielerParser();

            for(int i = 0; i < array.length(); i++){
                kader.add(parser.parse(array.getJSONObject(i)));
            }
            Log.d("SpielerListparser--------------------", "parse: " + kader.toString());
            return kader;
        } catch (JSONException e) {
            // Dieser Fall sollte nur auftreten, wenn das Feld für die Liste von Surveys nicht
            // existiert oder falsche Inhalte hat. Die ursprüngliche Exception wird in einer
            // ParsingException gekapselt.
            throw new ParsingException("Reading list of spieler failed.", e);
        }
    }
}
