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

    public SpielSpielerListItem(SpielSpieler stats) {
        this.stats = stats;
    }

    public SpielSpieler getStats() {
        return stats;
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
