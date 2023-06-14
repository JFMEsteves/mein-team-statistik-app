package de.fhswf.statistics.list.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielListItem;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.util.DateConverter;

/**
 * Viewholder von @link {@link SpielerListItem}
 */
public class SpielListViewHolder extends BaseViewHolder<SpielListItem>
        implements View.OnClickListener {

    private static final @ColorInt
    int BG_1 = Color.WHITE;
    private static final @ColorInt
    int BG_2 = 0x22000000;

    private SpielListItem currentSpiel;
    private final TextView datum, name, unserePunkte, gegnerPunkte;

    public SpielListViewHolder(@NonNull View itemView) {
        super(itemView);

        this.name = itemView.findViewById(R.id.name);
        this.datum = itemView.findViewById(R.id.date);
        this.unserePunkte = itemView.findViewById(R.id.our_points);
        this.gegnerPunkte = itemView.findViewById(R.id.enemy_points);

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


        // Alternierender Hintergrund
        itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_1 : BG_2);

    }
}
