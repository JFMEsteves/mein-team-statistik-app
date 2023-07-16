package de.fhswf.statistics.list.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielerSaisonListItem;
import de.fhswf.statistics.util.StatCalculator;

/**
 * Viewholder von @link {@link SpielerSaisonListItem}
 */
public class SpielerSaisonViewHolder extends BaseViewHolder<SpielerSaisonListItem> {
    private final TextView attendGames, gesamtpunkte, punkteProSpiel, dreier, freiwurfquote, gwfreiwuerfe, gtfreiwuerfe, fouls;

    public SpielerSaisonViewHolder(@NonNull View itemView) {
        super(itemView);
        this.attendGames = itemView.findViewById(R.id.attended_Games);
        this.gesamtpunkte = itemView.findViewById(R.id.all_points_season);
        this.punkteProSpiel = itemView.findViewById(R.id.points_per_game_season);
        this.dreier = itemView.findViewById(R.id.threes_made_season);
        this.freiwurfquote = itemView.findViewById(R.id.freethrows_percentage_season);
        this.gwfreiwuerfe = itemView.findViewById(R.id.freethrows_thrown_season);
        this.gtfreiwuerfe = itemView.findViewById(R.id.freethrows_made_season);
        this.fouls = itemView.findViewById(R.id.fouls_season);
    }


    @Override
    public void bind(SpielerSaisonListItem item) {
        attendGames.setText(String.valueOf(StatCalculator.attendedGamesCalc(item.getSpieler())));
        gesamtpunkte.setText(String.valueOf(StatCalculator.gesamtpunkteCalc(item.getSpieler())));
        punkteProSpiel.setText(Double.toString(StatCalculator.punktePerSpielCalc(item.getSpieler())));
        dreier.setText(String.valueOf(StatCalculator.dreierCalc(item.getSpieler())));
        freiwurfquote.setText(StatCalculator.freiwurfquoteCalc(item.getSpieler()));
        gwfreiwuerfe.setText(String.valueOf(StatCalculator.geworfeneFreiwuerfeCalc(item.getSpieler())));
        gtfreiwuerfe.setText(String.valueOf(StatCalculator.getroffeneFreiwuerfeCalc(item.getSpieler())));
        fouls.setText(String.valueOf(StatCalculator.foulsCalcSpieler(item.getSpieler())));
    }
}
