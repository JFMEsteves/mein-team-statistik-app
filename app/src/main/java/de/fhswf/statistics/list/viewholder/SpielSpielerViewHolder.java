package de.fhswf.statistics.list.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielSpielerListItem;

/**
 * Viewholder von @link {@link SpielSpielerListItem}
 */
public class SpielSpielerViewHolder extends BaseViewHolder<SpielSpielerListItem> {

    private static final @ColorInt
    int BG_1 = Color.WHITE;
    private final @ColorInt
    int colorXboxgrey = ContextCompat.getColor(itemView.getContext(), R.color.xbox_grey);
    private static final @ColorInt
    int BG_2 = 0x22000000;
    private final @ColorInt
    int colorOrange = ContextCompat.getColor(itemView.getContext(), R.color.orange);
    private final @ColorInt
    int colorGreen = ContextCompat.getColor(itemView.getContext(), R.color.light_green);
    private final @ColorInt
    int colorRed = ContextCompat.getColor(itemView.getContext(), R.color.redish);

    private final @ColorInt
    int colorBase = ContextCompat.getColor(itemView.getContext(), R.color.TextViewBase);

    private final @ColorInt
    int colorBaseDark = ContextCompat.getColor(itemView.getContext(), R.color.TextViewBaseDark);


    private final TextView spielid, punkte, madeFreethrows, shotFreethrows, freethrowPercantage, threePointmades, fouls;

    public SpielSpielerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.spielid = itemView.findViewById(R.id.HeaderId);
        this.punkte = itemView.findViewById(R.id.HeaderPoints);
        this.madeFreethrows = itemView.findViewById(R.id.HeaderFreethrowsMade);
        this.shotFreethrows = itemView.findViewById(R.id.HeaderFreethrowsThrown);
        this.freethrowPercantage = itemView.findViewById(R.id.freethrows_percentage);
        this.threePointmades = itemView.findViewById(R.id.HeaderThreesMade);
        this.fouls = itemView.findViewById(R.id.HeaderFoulsDetails);
    }

    @Override
    public void bind(@NonNull SpielSpielerListItem item) {
        if (item.isSpiel()) {
            spielid.setText(item.getStats().getSpieler().getName());
        } else if (!item.isSpiel()) {
            spielid.setText(item.getStats().getSpiel().getTeamname());
        }
        punkte.setText(String.valueOf(item.getStats().getPunkte()));
        madeFreethrows.setText(String.valueOf(item.getStats().getGetroffeneFreiwuerfe()));
        shotFreethrows.setText(String.valueOf(item.getStats().getGeworfeneFreiwuerfe()));
        int nightModeFlags = itemView.getContext().getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;

        // Farbliche Hervorhebung der Freiwurfquote
        if (item.getStats().getGeworfeneFreiwuerfe() != 0) {
            double freethrowPercentageDouble = (double) item.getStats().getGetroffeneFreiwuerfe() / item.getStats().getGeworfeneFreiwuerfe() * 100;
            double rounded = Math.round(freethrowPercentageDouble * 100.0) / 100.0;
            if (rounded >= 70) freethrowPercantage.setTextColor(colorGreen);
            else if (rounded >= 50) freethrowPercantage.setTextColor(colorOrange);
            else if (rounded < 50) freethrowPercantage.setTextColor(colorRed);
            String placeholder = String.format("%.2f", rounded);
            freethrowPercantage.setText(placeholder);
            //TODO Lösung für die schneinbar wirkürliche Farbänderung finden (siehe 0.00%) ohne das nightmode-if-statement
        } else if (item.getStats().getGeworfeneFreiwuerfe() == 0) {
            // Textfarbe muss gesetzt werden, weil sonst eine falsche Farbe übernommen wird
            // woher genau diese Farbe kommt ist mir nicht bekannt, jedoch funktioniert der "übermalen" approach.
            if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                freethrowPercantage.setTextColor(colorBaseDark);
            } else {
                freethrowPercantage.setTextColor(colorBase);
            }
            freethrowPercantage.setText("0.00");
        }
        threePointmades.setText(String.valueOf(item.getStats().getDreiPunkteTreffer()));
        fouls.setText(String.valueOf(item.getStats().getFouls()));
        if (item.getStats().getFouls() == 5) fouls.setTextColor(Color.RED);

        // Alternierender Hintergrund
        if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? colorXboxgrey : BG_2);
        } else {
            itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_1 : BG_2);
        }

    }
}
