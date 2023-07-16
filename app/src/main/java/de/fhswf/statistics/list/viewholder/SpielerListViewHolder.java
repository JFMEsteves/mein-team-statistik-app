package de.fhswf.statistics.list.viewholder;

import static java.lang.Double.parseDouble;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

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
    int BG_3 = Color.GRAY;
    private static final @ColorInt
    int BG_2 = 0x22000000;

    private final @ColorInt
    int colorOrange = ContextCompat.getColor(itemView.getContext(), R.color.orange);
    private final @ColorInt
    int colorGreen = ContextCompat.getColor(itemView.getContext(), R.color.light_green);
    private final @ColorInt
    int colorRed = ContextCompat.getColor(itemView.getContext(), R.color.redish);
    private SpielerListItem currentSpieler;
    private final TextView name, gesamtpunkte, punkteProSpiel, freiwurfquote, fouls;

    public SpielerListViewHolder(@NonNull View itemView) {
        super(itemView);

        this.name = itemView.findViewById(R.id.name);
        this.gesamtpunkte = itemView.findViewById(R.id.all_points);
        this.punkteProSpiel = itemView.findViewById(R.id.points_per_game);
        this.freiwurfquote = itemView.findViewById(R.id.freethrow_percentage);
        this.fouls = itemView.findViewById(R.id.fouls);
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
        String quote = StatCalculator.freiwurfquoteCalc(item.getSpieler(), false);
        double statcheck = parseDouble(quote);
        if (StatCalculator.geworfeneFreiwuerfeCalc(item.getSpieler()) != 0) {
            if (statcheck >= 75.0)
                freiwurfquote.setTextColor(colorGreen);
            else if (statcheck >= 50.0)
                freiwurfquote.setTextColor(colorOrange);
            else if (statcheck < 50)
                freiwurfquote.setTextColor(colorRed);
        }
        freiwurfquote.setText(quote + "%");
        fouls.setText(String.valueOf(StatCalculator.foulsCalcSpieler(item.getSpieler())));

        // Alternierender Hintergrund

        int nightModeFlags = itemView.getContext().getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_3 : BG_2);
        } else {
            itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_1 : BG_2);
        }
    }
}
