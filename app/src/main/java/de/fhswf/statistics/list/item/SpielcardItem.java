package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.SpielSpieler;

public class SpielcardItem implements ListItem, SpielerSubmitItem {
    public static final int TYPE = 2;

    @Nullable
    private Spiel spiel;

    public SpielcardItem(@NonNull Spiel spiel) {
        this.spiel = spiel;
    }

    public SpielcardItem() {

    }

    @Override
    public int getType() {
        return TYPE;
    }

    public Spiel getSpiel() {
        return spiel;
    }

    @Nullable
    @Override
    public JSONObject getResult() throws JSONException {
        //TODO Macht das Sinn ?

        JSONObject kader = new JSONObject();
        for (SpielSpieler c : spiel.getStats()) {
            kader.put("spielerid",c.getSpielerId())
                    .put("spielid",spiel.getId())
                    .put("punkte",c.getPunkte())
                    .put("geworfeneFreiwuerfe",c.getGeworfeneFreiwuerfe())
                    .put("getroffeneFreiwuerfe",c.getGetroffeneFreiwuerfe())
                    .put("dreiPunkteTreffer",c.getDreiPunkteTreffer())
                    .put("fouls",c.getFouls());
        }

        return new JSONObject()
                .put("id", spiel.getId())
                .put("name", spiel.getTeamname())
                .put("datum", spiel.getDatum())
                .put("gegnerPunkte", spiel.getGastPunkte())
                .put("unserePunkte", spiel.getHeimPunkte())
                .put("kader", kader);
    }
}
