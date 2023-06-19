package de.fhswf.statistics.api.parser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;


/**
 * Parser für einzelne Fragen.
 */
public class StatParser implements ResponseParser<SpielSpieler> {

    @NotNull
    @Override
    public SpielSpieler parse(@NotNull JSONObject data) throws ParsingException {
        try {
            JSONObject spielObject = data.getJSONObject("spiel");
            JSONObject spielerObject = data.getJSONObject("spieler");


            SpielSpieler stat = new SpielSpieler(data.optInt("spielerId"), data.optInt("spielId"));
            stat.setPunkte(data.optInt("punkte"));
            stat.setGeworfeneFreiwuerfe(data.optInt("geworfeneFreiwuerfe"));
            stat.setGetroffeneFreiwuerfe(data.optInt("getroffeneFreiwuerfe"));
            stat.setDreiPunkteTreffer(data.optInt("dreiPunkteTreffer"));
            stat.setFouls(data.optInt("fouls"));

            Spiel spiel = new Spiel();
            spiel.setId(spielObject.optInt("id"));
            spiel.setTeamname(spielObject.optString("name"));
            stat.setSpiel(spiel);
            Spieler spieler = new Spieler(spielerObject.optInt("id"),spielerObject.optString("name") );
            stat.setSpieler(spieler);

            // Parse generic question details
            // JSON.get(...) >> Mandatory field; throws exception when missing
            // JSON.opt(...) >> Optional field with default/fallback value


            return stat;
        } catch (Exception e) {
            throw new ParsingException("Parsing Statistik failed.", e);
        }
    }
}
