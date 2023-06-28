package de.fhswf.statistics.list.viewholder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielDetailsItem;

public class SpielDetailsViewHolder extends BaseViewHolder<SpielDetailsItem> {
    private static final @ColorInt
    int BG_1 = Color.WHITE;
    private static final @ColorInt
    int BG_3 = Color.GRAY;
    private static final @ColorInt
    int BG_2 = 0x22000000;
    private final TextView teamname, viertel1, viertel2, viertel3, viertel4, gesamtpunkte;

    public SpielDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.teamname = itemView.findViewById(R.id.teamname);
        this.viertel1 = itemView.findViewById(R.id.viertel1);
        this.viertel2 = itemView.findViewById(R.id.viertel2);
        this.viertel3 = itemView.findViewById(R.id.viertel3);
        this.viertel4 = itemView.findViewById(R.id.viertel4);
        this.gesamtpunkte = itemView.findViewById(R.id.gesamtpunkte);
    }

    @Override
    public void bind(SpielDetailsItem item) {


        int gegnerpunkte = item.getSpieldetails().getViertel1() + item.getSpieldetails().getViertel2() + item.getSpieldetails().getViertel3() + item.getSpieldetails().getViertel4();
        if (item.getSpieldetails().getEnemy() != 0) {
            teamname.setText(item.getSpieldetails().getSpiel().getTeamname());
        } else {
            teamname.setText(itemView.getResources().getString(R.string.herren));
        }
        gesamtpunkte.setText(String.valueOf(gegnerpunkte));
        viertel1.setText(String.valueOf(item.getSpieldetails().getViertel1()));
        viertel2.setText(String.valueOf(item.getSpieldetails().getViertel2()));
        viertel3.setText(String.valueOf(item.getSpieldetails().getViertel3()));
        viertel4.setText(String.valueOf(item.getSpieldetails().getViertel4()));

        // Alternierender Hintergrund
        int nightModeFlags = itemView.getContext().getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_3 : BG_2);
        } else {
            itemView.setBackgroundColor((getAdapterPosition() % 2 == 0) ? BG_1 : BG_2);
        }
    }
}
