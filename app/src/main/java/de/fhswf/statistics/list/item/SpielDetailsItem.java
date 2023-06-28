package de.fhswf.statistics.list.item;

import de.fhswf.statistics.model.Spieldetails;

/**
 * Klasse für den Saison Tab von {@link de.fhswf.statistics.list.fragments.SeasonFragment}
 * <p>
 * Viewholder:
 *
 * @see de.fhswf.statistics.list.viewholder.SpielerSaisonViewHolder
 */
public class SpielDetailsItem implements ListItem {
    public static final int TYPE = 6;
    private final Spieldetails spieldetails;

    public SpielDetailsItem(Spieldetails spieldetails) {
        this.spieldetails = spieldetails;
    }

    @Override
    public int getType() {
        return TYPE;
    }


    public Spieldetails getSpieldetails() {
        return spieldetails;
    }
}
