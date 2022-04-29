package de.fhswf.statistics.list.item;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import de.fhswf.statistics.model.Spiel;

public class SpielcardItem implements ListItem {
    public static final int TYPE = 2;

    @Nullable
    private Spiel spiel;

    public SpielcardItem(@NonNull Spiel spiel) {
        this.spiel = spiel;
    }

    public SpielcardItem() {

    }

    @Override
    public int getType() {
        return TYPE;
    }

    public Spiel getSpiel() {
        return spiel;
    }
}
