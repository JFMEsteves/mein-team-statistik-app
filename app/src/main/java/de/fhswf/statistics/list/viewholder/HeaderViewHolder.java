package de.fhswf.statistics.list.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.HeaderListItem;

@Deprecated
public class HeaderViewHolder extends BaseViewHolder<HeaderListItem> {
   public TextView headername, headerallpoints, headerpoints_per_game, header_freethrows;

    public HeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        headername = itemView.findViewById(R.id.HeaderName);
        headerallpoints = itemView.findViewById(R.id.HeaderAllPoints);
        headerpoints_per_game = itemView.findViewById(R.id.HeaderPointsPerGame);
        header_freethrows = itemView.findViewById(R.id.HeaderFreethrows);

    }

    @Override
    public void bind(HeaderListItem item) {
        headername.setText("Name Yonge");
        headerallpoints.setText("Punkte(ges.)");
        headerpoints_per_game.setText("Punkte pro Spiel");
        header_freethrows.setText("Freiwurfquote");


    }
}
