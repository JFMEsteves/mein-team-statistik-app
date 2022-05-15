package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Endkarte der NewGameActivity
 * Viewholder:
 *
 * @see de.fhswf.statistics.list.viewholder.EndcardViewHolder
 */
public class EndcardItem implements ListItem {
    public static final int TYPE = 10;

    private @Nullable
    OnEndClickListener onEndListener;

    /**
     * Getter Listener
     *
     * @return onEndListener
     */
    @Nullable
    public OnEndClickListener getOnEndListener() {
        return onEndListener;
    }

    public EndcardItem setOnEndListener(
            @Nullable OnEndClickListener onEndListener) {
        this.onEndListener = onEndListener;
        return this;
    }


    @Override
    public int getType() {
        return TYPE;
    }


    /**
     * Interface Listener für Spiel hinzufügen Button der EndCardItem.
     */
    public interface OnEndClickListener {
        void onEndButtonClick(@NonNull EndcardItem item);
    }

}
