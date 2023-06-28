package de.fhswf.statistics.list.item;

import de.fhswf.statistics.model.SpielSpieler;

/**
 * Viewholder:
 *
 * @see de.fhswf.statistics.list.viewholder.SpielSpielerViewHolder
 */
public class SpielSpielerListItem implements ListItem {
    public static final int TYPE = 3;

    private final SpielSpieler stats;
    private boolean isSpiel;

    public SpielSpielerListItem(SpielSpieler stats) {
        this.stats = stats;
    }

    public SpielSpielerListItem(SpielSpieler stats, boolean isSpiel) {
        this.stats = stats;
        this.isSpiel = isSpiel;
    }

    public SpielSpieler getStats() {
        return stats;
    }

    public boolean isSpiel() {
        return isSpiel;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
