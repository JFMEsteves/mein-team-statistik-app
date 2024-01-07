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
        this.attendGames = itemView.findViewById(R.id.HeaderAttendedGames);
        this.gesamtpunkte = itemView.findViewById(R.id.HeaderAllPointsSeason);
        this.punkteProSpiel = itemView.findViewById(R.id.HeaderPointsPerGameSeason);
        this.dreier = itemView.findViewById(R.id.HeaderThreesMadeSeason);
        this.freiwurfquote = itemView.findViewById(R.id.HeaderFreethrowPercentageSeason);
        this.gwfreiwuerfe = itemView.findViewById(R.id.HeaderFreethrowsThrownSeason);
        this.gtfreiwuerfe = itemView.findViewById(R.id.HeaderFreethrowsMadeSeason);
        this.fouls = itemView.findViewById(R.id.HeaderFoulsSeason);
    }


    @Override
    public void bind(SpielerSaisonListItem item) {
        attendGames.setText(String.valueOf(StatCalculator.attendedGamesCalc(item.getSpieler())));
        gesamtpunkte.setText(String.valueOf(StatCalculator.gesamtpunkteCalc(item.getSpieler())));
        punkteProSpiel.setText(Double.toString(StatCalculator.punktePerSpielCalc(item.getSpieler())));
        dreier.setText(String.valueOf(StatCalculator.dreierCalc(item.getSpieler())));
        freiwurfquote.setText(StatCalculator.freiwurfquoteCalc(item.getSpieler(), true));
        gwfreiwuerfe.setText(String.valueOf(StatCalculator.geworfeneFreiwuerfeCalc(item.getSpieler())));
        gtfreiwuerfe.setText(String.valueOf(StatCalculator.getroffeneFreiwuerfeCalc(item.getSpieler())));
        fouls.setText(String.valueOf(StatCalculator.foulsCalcSpieler(item.getSpieler())));
    }
}
