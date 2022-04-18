package de.fhswf.statistics.list.item;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import de.fhswf.statistics.model.Spieler;

public class SpielercardItem implements ListItem, SpielerInterfaceListItem, SpielerSubmitItem {
    public static final int TYPE = 4;

    @Nullable
    private final Spieler spieler;
    private String userInput;

    public SpielercardItem(Spieler spieler) {
        this.spieler = spieler;
    }


    public String getUserInput() {
        return userInput;
    }
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
    @Override
    public Spieler getSpieler() {
        return spieler;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Nullable
    @Override
    public JSONObject getResult() throws JSONException {
        return null;
    }
}
