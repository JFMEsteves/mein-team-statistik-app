package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EndaddGameListItem implements ListItem {
    //TODO Implement End-Karte
    public static final int TYPE = 10;
    private @Nullable
    OnEndClickListener onEndListener;
    private @Nullable
    OnResultsClickListener onResultsClickListener;
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
    @Nullable
    public OnResultsClickListener getOnResultsClickListener() {
        return onResultsClickListener;
    }
    public EndaddGameListItem setOnResultsClickListener(
            @Nullable OnResultsClickListener onResultsClickListener) {
        this.onResultsClickListener = onResultsClickListener;
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

    /**
     * Callback für den Ergebnisse-Anzeigen-Button.
     */
    public interface OnResultsClickListener {
        void onResultsClick();
    }
}
