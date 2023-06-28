package de.fhswf.statistics.list.item;

import de.fhswf.statistics.model.Spiel;

/**
 * Interface für ListItem-Klassen mit Spieler
 */
public interface SpielInterfaceListItem extends ListItem {

    Spiel getSpiel();
}
