package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;

import de.fhswf.statistics.model.Spieler;

public class SpielerListItem implements ListItem{
    public static final int TYPE = 1;

    private final Spieler spieler;
    private OnSpielerListener onSpielerListener;

    //TODO Listener


    public SpielerListItem(Spieler spieler) {
        this.spieler = spieler;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    public Spieler getSpieler() {
        return spieler;
    }



    public OnSpielerListener getOnSpielerListener() {
        return onSpielerListener;
    }

    public SpielerListItem setOnSpielerListener(OnSpielerListener onSpielerListener) {
        this.onSpielerListener = onSpielerListener;
        return this;
    }

    public interface OnSpielerListener {
        void onSpielerClick(@NonNull SpielerListItem item);
    }
}
