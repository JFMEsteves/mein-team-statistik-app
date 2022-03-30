package de.fhswf.statistics.list.viewholder;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Locale;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;
import de.fhswf.statistics.util.StatCalculator;

public class SpielerListViewHolder extends BaseViewHolder<SpielerListItem>
implements View.OnClickListener {

    private SpielerListItem currentSpieler;
    private final TextView name,gesamtpunkte,punkteProSpiel,freiwurfquote;
    private StatCalculator calculator;

    public SpielerListViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.name);
        this.gesamtpunkte = itemView.findViewById(R.id.all_points);
        this.punkteProSpiel = itemView.findViewById(R.id.points_per_game);
        this.freiwurfquote = itemView.findViewById(R.id.freethrow_percentage);
    }

    @Override
    public void onClick(View v) {
        if (currentSpieler.getOnSpielerListener() != null)
            currentSpieler.getOnSpielerListener().onSpielerClick(currentSpieler);
    }

    @Override
    public void bind(@NonNull SpielerListItem item) {
        this.currentSpieler = item;
        name.setText(item.getSpieler().getName());
       // freiwurfquote.setText(StatCalculator.freiwurfquote(item.getSpieler()));
        gesamtpunkte.setText(String.valueOf(calculator.gesamtpunkteCalc(item.getSpieler())));
        punkteProSpiel.setText(String.valueOf(calculator.punktePerSpielCalc(item.getSpieler())));
        freiwurfquote.setText(calculator.freiwurfquote(item.getSpieler()));

    }
}
