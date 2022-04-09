package de.fhswf.statistics.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhswf.statistics.NewGameActivity;
import de.fhswf.statistics.model.Spieler;

public class SpielerAuswahlDialog {
    private final @NonNull Context context;
    private final @NonNull List<Spieler> spielerList;
    private final AlertDialog dialog;
    private final Map<Integer, Boolean> auswahl = new HashMap<>();

    public SpielerAuswahlDialog(@NonNull Context context, @NonNull List<Spieler> spielerList) {
        this.context = context;
        this.spielerList = spielerList;

        // Dialog konfigurieren
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Spieler auswählen");

        // Auswahl vorbereiten
        CharSequence[] namen = new CharSequence[spielerList.size()];
        for (int i = 0; i < spielerList.size(); i++) {
            namen[i] = spielerList.get(i).getName();
        }

        builder.setMultiChoiceItems(namen, new boolean[spielerList.size()],
                (dialog, which, isChecked) -> auswahl.put(which, isChecked));

        // Buttons
        builder.setPositiveButton("Spiel erstellen", (d, w) -> handleClick());
        builder.setNeutralButton("Abbrechen", (d, w) -> d.cancel());

        // Anzeigen
        this.dialog = builder.create();
        dialog.show();
    }

    /**
     * TODO
     */
    private void handleClick() {
        ArrayList<Integer> spielerIds = new ArrayList<>();
        auswahl.forEach((k, v) -> {
            if(v) spielerIds.add(spielerList.get(k).getId());
        });

        Intent launchIntent = new Intent(context, NewGameActivity.class);
        launchIntent.putIntegerArrayListExtra(NewGameActivity.EXTRA_SPIELER_IDLIST, spielerIds);
        context.startActivity(launchIntent);

        dialog.dismiss();
    }
}
