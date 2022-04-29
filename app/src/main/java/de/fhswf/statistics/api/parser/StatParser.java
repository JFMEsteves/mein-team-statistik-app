package de.fhswf.statistics.api.parser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;


import de.fhswf.statistics.model.SpielSpieler;


/**
 * Parser für einzelne Fragen.
 */
public class StatParser implements ResponseParser<SpielSpieler> {

    @NotNull
    @Override
    public SpielSpieler parse(@NotNull JSONObject data) throws ParsingException {
        try {


            SpielSpieler stat = new SpielSpieler(data.optInt("spielerId"), data.optInt("spielId"));
            stat.setPunkte(data.optInt("punkte"));
            stat.setGeworfeneFreiwuerfe(data.optInt("geworfeneFreiwuerfe"));
            stat.setGetroffeneFreiwuerfe(data.optInt("getroffeneFreiwuerfe"));
            stat.setDreiPunkteTreffer(data.optInt("dreiPunkteTreffer"));
            stat.setFouls(data.optInt("fouls"));

            // Parse generic question details
            // JSON.get(...) >> Mandatory field; throws exception when missing
            // JSON.opt(...) >> Optional field with default/fallback value


            return stat;
        } catch (Exception e) {
            throw new ParsingException("Parsing Statistik failed.", e);
        }
    }
}
