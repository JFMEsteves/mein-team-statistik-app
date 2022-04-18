package de.fhswf.statistics.list.item;

import de.fhswf.statistics.model.Spieler;

public class SpielerSaisonListItem implements SpielerInterfaceListItem {
    public static final int TYPE = 8;
    private final Spieler spieler;

    public SpielerSaisonListItem(Spieler spieler) {
        this.spieler = spieler;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public Spieler getSpieler() {
        return spieler;
    }
}
