package de.fhswf.statistics.list.viewholder;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Objects;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielercardItem;
import de.fhswf.statistics.util.SimpleUpdateTextWatcher;

/**
 * Viewholder von @link {@link SpielercardItem}
 */
public class SpielercardViewHolder extends BaseViewHolder<SpielercardItem> {
    private final TextView name, decription;

    // private List<SimpleUpdateTextWatcher> textWatchers = new ArrayList<>();
    private SimpleUpdateTextWatcher watcherPunkte, watcherDreier, watcherGeworfeneFreiwuerfe, watcherGetroffeneFreiwuerfe, watcherFouls;
    private final EditText punkteInput, dreierInput, geworfeneFreiwuerfeInput, getroffeneFreiwuerfeInput, foulsInput;

    public SpielercardViewHolder(@NonNull View itemView) {
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
        clearTextWatchers();
        Objects.requireNonNull(item.getSpielSpieler()).setSpielerId(item.getSpieler().getId());
        name.setText(item.getSpieler().getName());

        initTextWatchers(item);


    }

    /**
     * initialisiert die nötigen TextWatcher und füllt das item bei Eingaben über diesen
     *
     * @param item Spielercarditem
     */
    private void initTextWatchers(SpielercardItem item) {
        // Punkte
        if (item.getSpielSpieler().getPunkte() != 0)
            punkteInput.setText(String.valueOf(item.getSpielSpieler().getPunkte()));
        else if (item.getSpielSpieler().getPunkte() == 0) punkteInput.setText(null);
      /*
        punkteInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty()) {
                        item.getSpielSpieler().setPunkte(Integer.parseInt(t));
                        Log.d("SPIELERCARDADD", "detected Update: " + t + " value: " + item.getSpielSpieler().getPunkte() + " Spieler: " + item.getSpieler().getName());
                    }
                }));

*/
        watcherPunkte = new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty())
                        item.getSpielSpieler().setPunkte(Integer.parseInt(t));
                });

        // Dreier
        if (item.getSpielSpieler().getDreiPunkteTreffer() != 0)
            dreierInput.setText(String.valueOf(item.getSpielSpieler().getDreiPunkteTreffer()));
        else if (item.getSpielSpieler().getDreiPunkteTreffer() == 0) dreierInput.setText(null);
        watcherDreier = new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty())
                        item.getSpielSpieler().setDreiPunkteTreffer(Integer.parseInt(t));
                });


        // geworfene Freiwürfe
        if (item.getSpielSpieler().getGeworfeneFreiwuerfe() != 0)
            geworfeneFreiwuerfeInput.setText(String.valueOf(item.getSpielSpieler().getGeworfeneFreiwuerfe()));
        else if (item.getSpielSpieler().getGeworfeneFreiwuerfe() == 0)
            geworfeneFreiwuerfeInput.setText(null);
        watcherGeworfeneFreiwuerfe = new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty())
                        item.getSpielSpieler().setGeworfeneFreiwuerfe(Integer.parseInt(t));
                });

        /*
        getroffeneFreiwuerfeInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty())
                        item.getSpielSpieler().setGetroffeneFreiwuerfe(Integer.parseInt(t));
                }));


         */

        // getroffene Freiwürfe
        if (item.getSpielSpieler().getGetroffeneFreiwuerfe() != 0)
            getroffeneFreiwuerfeInput.setText(String.valueOf(item.getSpielSpieler().getGetroffeneFreiwuerfe()));
        else if (item.getSpielSpieler().getGetroffeneFreiwuerfe() == 0)
            getroffeneFreiwuerfeInput.setText(null);
        watcherGetroffeneFreiwuerfe = new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty())
                        item.getSpielSpieler().setGetroffeneFreiwuerfe(Integer.parseInt(t));
                });

        /*
        geworfeneFreiwuerfeInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty())
                        item.getSpielSpieler().setGeworfeneFreiwuerfe(Integer.parseInt(t));
                }));



         */
        // Fouls
        if (item.getSpielSpieler().getFouls() != 0)
            foulsInput.setText(String.valueOf(item.getSpielSpieler().getFouls()));
        else if (item.getSpielSpieler().getFouls() == 0) foulsInput.setText(null);
        watcherFouls = new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty())
                        item.getSpielSpieler().setFouls(Integer.parseInt(t));
                });

        /*
        foulsInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty())
                        item.getSpielSpieler().setFouls(Integer.parseInt(t));
                }));


         */
        punkteInput.addTextChangedListener(watcherPunkte);
        dreierInput.addTextChangedListener(watcherDreier);
        geworfeneFreiwuerfeInput.addTextChangedListener(watcherGeworfeneFreiwuerfe);
        getroffeneFreiwuerfeInput.addTextChangedListener(watcherGetroffeneFreiwuerfe);
        foulsInput.addTextChangedListener(watcherFouls);

        // textWatchers.add(watcherPunkte);
        // textWatchers.add(watcherDreier);
        // textWatchers.add(watcherGeworfeneFreiwuerfe);
        // textWatchers.add(watcherGetroffeneFreiwuerfe);
        //  textWatchers.add(watcherFouls);
    }

    public void clearTextWatchers() {
       /* for(SimpleUpdateTextWatcher watcher : textWatchers){
            punkteInput.removeTextChangedListener(watcher);
            dreierInput.removeTextChangedListener(watcher);
            geworfeneFreiwuerfeInput.removeTextChangedListener(watcher);
            getroffeneFreiwuerfeInput.removeTextChangedListener(watcher);
            foulsInput.removeTextChangedListener(watcher);
            textWatchers.clear();
        }

        */
        punkteInput.removeTextChangedListener(watcherPunkte);
        dreierInput.removeTextChangedListener(watcherDreier);
        geworfeneFreiwuerfeInput.removeTextChangedListener(watcherGeworfeneFreiwuerfe);
        getroffeneFreiwuerfeInput.removeTextChangedListener(watcherGetroffeneFreiwuerfe);
        foulsInput.removeTextChangedListener(watcherFouls);

    }
}
