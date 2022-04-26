package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

public class SpielercardItem implements ListItem, SpielerInterfaceListItem, SpielerSubmitItem {
    public static final int TYPE = 4;

    @Nullable
    private final Spieler spieler;
    @Nullable
    private SpielSpieler spielSpieler;
    private String userInput;

    public SpielercardItem(@NonNull Spieler spieler) {
        this.spieler = spieler;
    }

    public SpielercardItem(@Nullable Spieler spieler, @Nullable SpielSpieler spielSpieler) {
        this.spieler = spieler;
        this.spielSpieler = spielSpieler;
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
       return null;
    }
}
