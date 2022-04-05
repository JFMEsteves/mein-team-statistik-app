package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EndaddGameListItem implements ListItem {
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

    public EndaddGameListItem setOnEndListener(
            @Nullable OnEndClickListener onEndListener) {
        this.onEndListener = onEndListener;
        return this;
    }


    @Override
    public int getType() {
        return TYPE;
    }


    /**
     * Interface Listener für Umfrage-Beenden-Button auf EndQuestion Item.
     */
    public interface OnEndClickListener {
        void onEndButtonClick(@NonNull EndaddGameListItem item);
    }

}
