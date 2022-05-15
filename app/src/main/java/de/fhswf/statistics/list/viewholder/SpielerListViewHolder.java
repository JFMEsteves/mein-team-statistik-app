package de.fhswf.statistics.list.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.util.StatCalculator;

/**
 * Viewholder von @link {@link SpielerListItem}
 */
public class SpielerListViewHolder extends BaseViewHolder<SpielerListItem>
        implements View.OnClickListener {

    private static final @ColorInt
    int BG_1 = Color.WHITE;
    private static final @ColorInt
    int BG_2 = 0x22000000;

    private SpielerListItem currentSpieler;
    private final TextView name, gesamtpunkte, punkteProSpiel, freiwurfquote;

    public SpielerListViewHolder(@NonNull View itemView) {
        super(itemView);

        this.name = itemView.findViewById(R.id.name);
        this.gesamtpunkte = itemView.findViewById(R.id.all_points);
        this.punkteProSpiel = itemView.findViewById(R.id.points_per_game);
        this.freiwurfquote = itemView.findViewById(R.id.freethrow_percentage);

        itemView.setOnClickListener(this);
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
        gesamtpunkte.setText(String.valueOf(StatCalculator.gesamtpunkteCalc(item.getSpieler())));
        punkteProSpiel.setText(String.valueOf(StatCalculator.punktePerSpielCalc(item.getSpieler())));
        freiwurfquote.setText(StatCalculator.freiwurfquoteCalc(item.getSpieler()));

        // Alternierender Hintergrund
        itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_1 : BG_2);

    }
}
