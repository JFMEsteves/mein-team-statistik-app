package de.fhswf.statistics.list.viewholder;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpieldetailscardItem;
import de.fhswf.statistics.util.SimpleUpdateTextWatcher;

/**
 * Viewholder von @link {@link SpieldetailscardItem}
 */
public class SpielDetailcardViewHolder extends BaseViewHolder<SpieldetailscardItem> {
    private final TextView name, description;
    private SimpleUpdateTextWatcher watcherViertel1, watcherViertel2, watcherViertel3, watcherViertel4;
    private final EditText viertel1Input, viertel2Input, viertel3Input, viertel4Input;

    public SpielDetailcardViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.team);
        this.description = itemView.findViewById(R.id.description);
        this.viertel1Input = itemView.findViewById(R.id.viertel1_input);
        this.viertel2Input = itemView.findViewById(R.id.viertel2_input);
        this.viertel3Input = itemView.findViewById(R.id.viertel3_input);
        this.viertel4Input = itemView.findViewById(R.id.viertel4_input);
    }

    @Override
    public void bind(SpieldetailscardItem item) {
        clearTextWatchers();

        if (item.isEnemy()) {
            name.setText(itemView.getResources().getString(R.string.gegner));
        } else {
            name.setText(itemView.getResources().getString(R.string.herren));
        }
        initTextWatcher(item);
    }

    /**
     * initialisiert die nötigen TextWatcher und füllt das item bei Eingaben über diesen
     *
     * @param item Spieldetails item
     */
    private void initTextWatcher(SpieldetailscardItem item) {
        if (item.getSpieldetails().getViertel1() != 0) {
            viertel1Input.setText(String.valueOf(item.getSpieldetails().getViertel1()));
        } else if (item.getSpieldetails().getViertel1() == 0) {
            viertel1Input.setText(null);
        }
        watcherViertel1 = new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty()) item.getSpieldetails().setViertel1(Integer.parseInt(t));
                });

        if (item.getSpieldetails().getViertel2() != 0) {
            viertel2Input.setText(String.valueOf(item.getSpieldetails().getViertel2()));
        } else if (item.getSpieldetails().getViertel2() == 0) {
            viertel2Input.setText(null);
        }
        watcherViertel2 = new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty()) item.getSpieldetails().setViertel2(Integer.parseInt(t));
                });

        if (item.getSpieldetails().getViertel3() != 0) {
            viertel3Input.setText(String.valueOf(item.getSpieldetails().getViertel3()));
        } else if (item.getSpieldetails().getViertel3() == 0) {
            viertel3Input.setText(null);
        }
        watcherViertel3 = new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty()) item.getSpieldetails().setViertel3(Integer.parseInt(t));
                });

        if (item.getSpieldetails().getViertel4() != 0) {
            viertel4Input.setText(String.valueOf(item.getSpieldetails().getViertel4()));
        } else if (item.getSpieldetails().getViertel4() == 0) {
            viertel4Input.setText(null);
        }
        watcherViertel4 = new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty()) item.getSpieldetails().setViertel4(Integer.parseInt(t));
                });
        viertel1Input.addTextChangedListener(watcherViertel1);
        viertel2Input.addTextChangedListener(watcherViertel2);
        viertel3Input.addTextChangedListener(watcherViertel3);
        viertel4Input.addTextChangedListener(watcherViertel4);


    }

    private void clearTextWatchers() {
        viertel1Input.removeTextChangedListener(watcherViertel1);
        viertel2Input.removeTextChangedListener(watcherViertel2);
        viertel3Input.removeTextChangedListener(watcherViertel3);
        viertel4Input.removeTextChangedListener(watcherViertel4);
    }
}
