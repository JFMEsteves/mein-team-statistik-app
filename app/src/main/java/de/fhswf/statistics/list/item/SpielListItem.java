package de.fhswf.statistics.list.item;

import androidx.annotation.Nullable;

import de.fhswf.statistics.model.Spiel;

public class SpielListItem implements ListItem {
    public static final int TYPE = 2;

    @Nullable
    private Spiel spiel;
    private String userInput;

    //TODO OnSpielListener erstellen, getter und Setter

    public String getUserInput() {
        return userInput;
    }
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public SpielListItem(Spiel spiel){this.spiel = spiel;}
    public SpielListItem(){

    }

    @Override
    public int getType() {
        return TYPE;
    }

    public Spiel getSpiel() {
        return spiel;
    }
}
