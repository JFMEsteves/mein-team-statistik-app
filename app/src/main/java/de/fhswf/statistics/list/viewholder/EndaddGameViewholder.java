package de.fhswf.statistics.list.viewholder;

import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.EndaddGameListItem;

public class EndaddGameViewholder extends BaseViewHolder<EndaddGameListItem> {

    private final ImageButton submit;

    public EndaddGameViewholder(@NonNull View itemView) {
        super(itemView);
        this.submit = itemView.findViewById(R.id.submit);
    }

    @Override
    public void bind(EndaddGameListItem item) {
        submit.setOnClickListener(v -> {
            if (item.getOnEndListener() != null)
                item.getOnEndListener().onEndButtonClick(item);
        });

    }
}
