package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import de.fhswf.statistics.model.Spiel;

public class SpielcardItem implements ListItem, SpielerSubmitItem {
    public static final int TYPE = 2;

    @Nullable
    private Spiel spiel;
    @Nullable
    private String userInput;


    @Nullable
    public String getUserInput() {
        return userInput;
    }
    public void setUserInput(@Nullable String userInput) {
        this.userInput = userInput;
    }

    public SpielcardItem(@NonNull Spiel spiel){this.spiel = spiel;}
    public SpielcardItem(){

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
        return null;
    }
}
