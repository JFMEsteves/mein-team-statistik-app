package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;

import de.fhswf.statistics.model.Spiel;

/**
 * Viewholder:
 *
 * @see de.fhswf.statistics.list.viewholder.SpielListViewHolder
 */
public class SpielListItem implements ListItem {
    public static final int TYPE = 5;

    private final Spiel spiel;
    private OnSpielListener onSpielListener;


    public SpielListItem(Spiel spiel) {
        this.spiel = spiel;
    }

    @Override
    public int getType() {
        return TYPE;
    }





    public OnSpielListener getOnSpielListener() {
        return onSpielListener;
    }

    public SpielListItem setOnSpielListener(OnSpielListener onSpielListener) {
        this.onSpielListener = onSpielListener;
        return this;
    }
    public Spiel getSpiel() {
        return spiel;
    }

    public interface OnSpielListener {
        void onSpielClick(@NonNull SpielListItem item);
    }
}
