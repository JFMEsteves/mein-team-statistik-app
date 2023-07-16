package de.fhswf.statistics.list.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielListItem;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.util.DateConverter;
import de.fhswf.statistics.util.StatCalculator;

/**
 * Viewholder von @link {@link SpielerListItem}
 */
public class SpielListViewHolder extends BaseViewHolder<SpielListItem>
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
    private SpielListItem currentSpiel;
    private final TextView datum, name, unserePunkte, gegnerPunkte, punkteDifferenz, teamfouls;

    public SpielListViewHolder(@NonNull View itemView) {
        super(itemView);

        this.name = itemView.findViewById(R.id.name);
        this.datum = itemView.findViewById(R.id.date);
        this.unserePunkte = itemView.findViewById(R.id.our_points);
        this.gegnerPunkte = itemView.findViewById(R.id.enemy_points);
        this.punkteDifferenz = itemView.findViewById(R.id.pointdifference);
        this.teamfouls = itemView.findViewById(R.id.teamfouls);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (currentSpiel.getOnSpielListener() != null)
            currentSpiel.getOnSpielListener().onSpielClick(currentSpiel);
    }

    @Override
    public void bind(@NonNull SpielListItem item) {
        this.currentSpiel = item;
        datum.setText(DateConverter.DateToString(item.getSpiel().getDatum()));
        name.setText(item.getSpiel().getTeamname());
        unserePunkte.setText(String.valueOf(item.getSpiel().getHeimPunkte()));
        gegnerPunkte.setText(String.valueOf(item.getSpiel().getGastPunkte()));
        int differenz = item.getSpiel().getHeimPunkte() - item.getSpiel().getGastPunkte();
        punkteDifferenz.setText(String.valueOf(differenz));
        teamfouls.setText(String.valueOf(StatCalculator.foulsCalcSpiel(item.getSpiel())));

        if (differenz > 10) {
            punkteDifferenz.setTextColor(colorGreen);
        } else if (differenz > -10 && differenz < 0) {
            punkteDifferenz.setTextColor(colorOrange);
        } else if (differenz < -15) {
            punkteDifferenz.setTextColor(colorRed);
        }
        // Alternierender Hintergrund
        // Alternierender Hintergrund
        int nightModeFlags = itemView.getContext().getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_3 : BG_2);
        } else {
            itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_1 : BG_2);
        }

    }
}
