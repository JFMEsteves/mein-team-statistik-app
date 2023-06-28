package de.fhswf.statistics.list.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielSpielerListItem;

/**
 * Viewholder von @link {@link SpielSpielerListItem}
 */
public class SpielSpielerViewHolder extends BaseViewHolder<SpielSpielerListItem> {

    private static final @ColorInt
    int BG_1 = Color.WHITE;
    private static final @ColorInt
    int BG_3 = Color.GRAY;
    private static final @ColorInt
    int BG_2 = 0x22000000;
    private final TextView spielid, punkte, madeFreethrows, shotFreethrows, freethrowPercantage, threePointmades, fouls;

    public SpielSpielerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.spielid = itemView.findViewById(R.id.GameID);
        this.punkte = itemView.findViewById(R.id.Points);
        this.madeFreethrows = itemView.findViewById(R.id.freethrows_made);
        this.shotFreethrows = itemView.findViewById(R.id.freethrows_thrown);
        this.freethrowPercantage = itemView.findViewById(R.id.freethrows_percentage);
        this.threePointmades = itemView.findViewById(R.id.threes_made);
        this.fouls = itemView.findViewById(R.id.fouls);
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
        if (item.getStats().getGeworfeneFreiwuerfe() != 0) {
            String placeholder = item.getStats().getGetroffeneFreiwuerfe() / item.getStats().getGeworfeneFreiwuerfe() * 100 + "%";
            freethrowPercantage.setText(placeholder);
        } else {
            freethrowPercantage.setText("0%");
        }
        threePointmades.setText(String.valueOf(item.getStats().getDreiPunkteTreffer()));
        fouls.setText(String.valueOf(item.getStats().getFouls()));

        // Alternierender Hintergrund
        int nightModeFlags = itemView.getContext().getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_3 : BG_2);
        } else {
            itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_1 : BG_2);
        }
        // itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_1 : BG_2);
    }
}
