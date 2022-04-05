package de.fhswf.statistics.list.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import de.fhswf.statistics.list.item.SpielerSaisonListItem;
import de.fhswf.statistics.list.item.SpielercardItem;
import de.fhswf.statistics.util.StatCalculator;

public class SpielerSaisonViewholder extends BaseViewHolder<SpielerSaisonListItem> {
    private SpielercardItem currentSpieler;
    private StatCalculator calculator;

    public SpielerSaisonViewholder(@NonNull View itemView) {
        super(itemView);
    }
    //TODO Erstellen


    @Override
    public void bind(SpielerSaisonListItem item) {

    }
}
