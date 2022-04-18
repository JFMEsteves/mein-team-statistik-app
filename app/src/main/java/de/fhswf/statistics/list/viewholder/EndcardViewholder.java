package de.fhswf.statistics.list.viewholder;

import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.EndcardItem;

public class EndcardViewholder extends BaseViewHolder<EndcardItem> {

    @NonNull
    private final ImageButton submit;

    public EndcardViewholder(@NonNull View itemView) {
        super(itemView);
        this.submit = itemView.findViewById(R.id.submit);
    }

    @Override
    public void bind(EndcardItem item) {
        submit.setOnClickListener(v -> {
            if (item.getOnEndListener() != null)
                item.getOnEndListener().onEndButtonClick(item);
        });

    }
}
