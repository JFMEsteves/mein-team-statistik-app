package de.fhswf.statistics.list.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielSpielerListItem;

public class SpielSpielerViewHolder extends BaseViewHolder<SpielSpielerListItem> {

    private SpielSpielerListItem currentSpiel;
    private final TextView spielid, punkte, madeFreethrows, shotFreethrows, freethrowPercantage, threePointmades, fouls;

    //TODO Testing
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
        this.currentSpiel = item;
        spielid.setText(String.valueOf(item.getStats().getSpielId()));
        punkte.setText(String.valueOf(item.getStats().getPunkte()));
        madeFreethrows.setText(String.valueOf(item.getStats().getGetroffeneFreiwuerfe()));
        shotFreethrows.setText(String.valueOf(item.getStats().getGeworfeneFreiwuerfe()));
        String placeholder = item.getStats().getGetroffeneFreiwuerfe() / item.getStats().getGeworfeneFreiwuerfe() * 100 + "%";
        freethrowPercantage.setText(placeholder);
        threePointmades.setText(String.valueOf(item.getStats().getDreiPunkteTreffer()));
        fouls.setText(String.valueOf(item.getStats().getFouls()));

    }
}
