package de.fhswf.statistics.list.item;

import de.fhswf.statistics.model.Spieler;

/**
 * Interface für ListItem-Klassen mit Spieler
 */
public interface SpielerInterfaceListItem extends ListItem {

    Spieler getSpieler();
}
