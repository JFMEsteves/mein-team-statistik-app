package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.Spieldetails;

public class SpieldetailscardItem implements SpieldetailsSubmitItem, SpielInterfaceListItem {
    public static final int TYPE = 9;

    @Nullable
    private final Spiel spiel;

    @Nullable
    private Spieldetails spieldetails;

    private boolean isEnemy;

    public SpieldetailscardItem(@NonNull Spiel spiel, boolean isEnemy) {
        this.spiel = spiel;
        this.isEnemy = isEnemy;
    }

    public SpieldetailscardItem(@Nullable Spiel spiel, @Nullable Spieldetails spieldetails, boolean isEnemy) {
        this.spiel = spiel;
        this.spieldetails = spieldetails;
        this.isEnemy = isEnemy;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    @Override
    @Nullable
    public Spiel getSpiel() {
        return spiel;
    }

    @Nullable
    public Spieldetails getSpieldetails() {
        return spieldetails;
    }

    @Nullable
    @Override
    public JSONObject getResult() throws JSONException {
        JSONObject details = new JSONObject();
        details.put("detailsId", getSpieldetails().getId())
                .put("enemy", getSpieldetails().getEnemy())
                .put("viertel1", getSpieldetails().getViertel1())
                .put("viertel2", getSpieldetails().getViertel2())
                .put("viertel3", getSpieldetails().getViertel3())
                .put("viertel4", getSpieldetails().getViertel4());
        return details;
    }
}
