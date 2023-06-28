package de.fhswf.statistics.api.parser;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.Spieldetails;

/**
 * Parser für einzelne SpielSpieler Objekte.
 */
public class DetailsParser implements ResponseParser<Spieldetails> {
    @Override
    public Spieldetails parse(@NonNull JSONObject data) throws ParsingException {
        try {
            Log.d("DETAILS PARSER", "Anfang des Parsers");
            JSONObject spielObject = data.getJSONObject("spielname");
            Spieldetails details = new Spieldetails(
                    data.getInt("detailsId"), data.getInt("enemy"));
            details.setViertel1(data.getInt("viertel1"));
            details.setViertel2(data.getInt("viertel2"));
            details.setViertel3(data.getInt("viertel3"));
            details.setViertel4(data.getInt("viertel4"));

            Spiel spiel = new Spiel();
            spiel.setId(spielObject.optInt("id"));
            spiel.setTeamname(spielObject.optString("name"));
            details.setSpiel(spiel);

            // Parse generic question details
            // JSON.get(...) >> Mandatory field; throws exception when missing
            // JSON.opt(...) >> Optional field with default/fallback value


            return details;
        } catch (Exception e) {
            throw new ParsingException("Parsing Statistik failed.", e);
        }
    }
}
