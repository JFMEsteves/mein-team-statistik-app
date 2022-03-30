package de.fhswf.statistics.list.item;

import de.fhswf.statistics.model.Spieler;

public class SpielerItem implements ListItem, SpielerInterfaceListItem {
    public static final int TYPE = 4;

    private final Spieler spieler;
    private String userInput;

    public SpielerItem(Spieler spieler) {
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
}
