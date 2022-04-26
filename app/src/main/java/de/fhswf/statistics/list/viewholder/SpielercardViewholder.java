package de.fhswf.statistics.list.viewholder;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielercardItem;

public class SpielercardViewholder extends BaseViewHolder<SpielercardItem> {
    private final TextView name, decription;
    private final EditText punkteInput, dreierInput, geworfeneFreiwuerfeInput, getroffeneFreiwuerfeInput, foulsInput;

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
        //TODO Testing
        initTextWatcher(item);
        item.getSpielSpieler().setSpielerId(item.getSpieler().getId());
        name.setText(item.getSpieler().getName());


    }

    //TODO Unterschiedliche Textwatcher ?
    private void initTextWatcher(SpielercardItem item) {
        TextWatcher textWatcherPunkte = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override // Delimter ; um daten nachher zu trennen
            public void afterTextChanged(Editable s) {
                //  item.setUserInput(s.toString() + ";");
                item.getSpielSpieler().setPunkte(Integer.parseInt(s.toString()));
            }
        };
        TextWatcher textWatcherDreier = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override // Delimter ; um daten nachher zu trennen
            public void afterTextChanged(Editable s) {
                //  item.setUserInput(s.toString() + ";");
                item.getSpielSpieler().setDreiPunkteTreffer(Integer.parseInt(s.toString()));
            }
        };
        TextWatcher textWatchergtFreiwuerfeInput = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override // Delimter ; um daten nachher zu trennen
            public void afterTextChanged(Editable s) {
                //  item.setUserInput(s.toString() + ";");
                item.getSpielSpieler().setGetroffeneFreiwuerfe(Integer.parseInt(s.toString()));
            }
        };
        TextWatcher textWatchergwFreiwuerfeInputPunkte = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override // Delimter ; um daten nachher zu trennen
            public void afterTextChanged(Editable s) {
                //  item.setUserInput(s.toString() + ";");
                item.getSpielSpieler().setGeworfeneFreiwuerfe(Integer.parseInt(s.toString()));
            }
        };
        TextWatcher textWatcherFouls = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override // Delimter ; um daten nachher zu trennen
            public void afterTextChanged(Editable s) {
                //  item.setUserInput(s.toString() + ";");
                item.getSpielSpieler().setFouls(Integer.parseInt(s.toString()));
            }
        };
        punkteInput.addTextChangedListener(textWatcherPunkte);
        dreierInput.addTextChangedListener(textWatcherDreier);
        getroffeneFreiwuerfeInput.addTextChangedListener(textWatchergtFreiwuerfeInput);
        geworfeneFreiwuerfeInput.addTextChangedListener(textWatchergwFreiwuerfeInputPunkte);
        foulsInput.addTextChangedListener(textWatcherFouls);

    }
}
