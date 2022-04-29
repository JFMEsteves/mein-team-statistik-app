package de.fhswf.statistics.list.viewholder;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielercardItem;
import de.fhswf.statistics.util.SimpleUpdateTextWatcher;

public class SpielercardViewholder extends BaseViewHolder<SpielercardItem> {
    private final TextView name, decription;
    private EditText punkteInput, dreierInput, geworfeneFreiwuerfeInput, getroffeneFreiwuerfeInput, foulsInput;

    public SpielercardViewholder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.player_name);
        this.decription = itemView.findViewById(R.id.description);
        this.punkteInput = itemView.findViewById(R.id.punkteInput);
        this.dreierInput = itemView.findViewById(R.id.threes_input);
        this.getroffeneFreiwuerfeInput = itemView.findViewById(R.id.freethrows_made_input);
        this.geworfeneFreiwuerfeInput = itemView.findViewById(R.id.freethrows_thrown_input);
        this.foulsInput = itemView.findViewById(R.id.fouls_input);
    }

    @Override
    public void bind(SpielercardItem item) {
        initTextWatcher(item);
        item.getSpielSpieler().setSpielerId(item.getSpieler().getId());
        name.setText(item.getSpieler().getName());


    }

    private void initTextWatcher(SpielercardItem item) {
        punkteInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> item.getSpielSpieler().setPunkte(Integer.parseInt(t))));
        dreierInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> item.getSpielSpieler().setDreiPunkteTreffer(Integer.parseInt(t))));
        getroffeneFreiwuerfeInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> item.getSpielSpieler().setGetroffeneFreiwuerfe(Integer.parseInt(t))));
        geworfeneFreiwuerfeInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> item.getSpielSpieler().setGeworfeneFreiwuerfe(Integer.parseInt(t))));
        foulsInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> item.getSpielSpieler().setFouls(Integer.parseInt(t))));

    }
}
