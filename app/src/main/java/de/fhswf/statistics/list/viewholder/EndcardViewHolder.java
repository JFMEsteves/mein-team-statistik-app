package de.fhswf.statistics.list.viewholder;

import android.view.View;
import android.widget.ImageButton;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.EndcardItem;

/**
 * Viewholder von @link {@link EndcardItem}
 */
public class EndcardViewHolder extends BaseViewHolder<EndcardItem> {


    private final ImageButton submit;

    public EndcardViewHolder(View itemView) {
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
