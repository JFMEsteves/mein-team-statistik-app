package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

/**
 * Klasse für die Spielerkarte
 * Viewholder:
 *
 * @see de.fhswf.statistics.list.viewholder.SpielercardViewHolder
 */
public class SpielercardItem implements SpielerInterfaceListItem, SpielerSubmitItem {
    public static final int TYPE = 4;

    @Nullable
    private final Spieler spieler;
    @Nullable
    private SpielSpieler spielSpieler;


    public SpielercardItem(@NonNull Spieler spieler) {
        this.spieler = spieler;
    }

    public SpielercardItem(@Nullable Spieler spieler, @Nullable SpielSpieler spielSpieler) {
        this.spieler = spieler;
        this.spielSpieler = spielSpieler;
    }

    @Override
    public Spieler getSpieler() {
        return spieler;
    }

    @Nullable
    public SpielSpieler getSpielSpieler() {
        return spielSpieler;
    }

    public void setSpielSpieler(@NonNull SpielSpieler spielSpieler) {
        this.spielSpieler = spielSpieler;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Nullable
    @Override
    public JSONObject getResult() throws JSONException {
        JSONObject kader = new JSONObject();
        kader.put("spielerId", getSpielSpieler().getSpielerId())
                .put("spielId", spielSpieler == null ? 0 : spielSpieler.getSpielId())
                .put("punkte", getSpielSpieler().getPunkte())
                .put("geworfeneFreiwuerfe", getSpielSpieler().getGeworfeneFreiwuerfe())
                .put("getroffeneFreiwuerfe", getSpielSpieler().getGetroffeneFreiwuerfe())
                .put("dreiPunkteTreffer", getSpielSpieler().getDreiPunkteTreffer())
                .put("fouls", getSpielSpieler().getFouls());
        return kader;
    }
}
