package de.fhswf.statistics.list.item;

import de.fhswf.statistics.model.Spiel;

public class SpielListItem implements ListItem {
    public static final int TYPE = 2;

    private final Spiel spiel;

    //TODO OnSpielListener erstellen, getter und Setter

    public SpielListItem(Spiel spiel){this.spiel = spiel;}

    @Override
    public int getType() {
        return TYPE;
    }

    public Spiel getSpiel() {
        return spiel;
    }
}
