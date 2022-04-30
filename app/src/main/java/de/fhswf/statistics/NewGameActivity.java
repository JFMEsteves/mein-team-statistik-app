package de.fhswf.statistics;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.fhswf.statistics.api.service.RemoteSpielerService;
import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.EndcardItem;
import de.fhswf.statistics.list.item.ListItem;
import de.fhswf.statistics.list.item.SpielcardItem;
import de.fhswf.statistics.list.item.SpielerSubmitItem;
import de.fhswf.statistics.list.item.SpielercardItem;
import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;
import de.fhswf.statistics.util.DateConverter;

public class NewGameActivity extends AppCompatActivity implements EndcardItem.OnEndClickListener {
    public static final String EXTRA_SPIELER_IDLIST = "spieler_ids";

    private boolean busy;
    private ArrayList<Integer> spielerIds;
    private SpielerService spielerService;
    private LinearLayoutManager layoutManager;
    private ListAdapter adapter;
    private int id;
    private Spiel spiel;
    private SpielcardItem spielcardItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent stuff
        Intent mainActivityIntent = getIntent();
        this.spielerIds = mainActivityIntent.getIntegerArrayListExtra(EXTRA_SPIELER_IDLIST);

        if (spielerIds == null) {
            throw new RuntimeException("Keine Spieler ausgewählt!");
        }

        //Setting up View
        setContentView(R.layout.add_game_view);
        RecyclerView container = findViewById(R.id.add_player_container);

        layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        container.setLayoutManager(layoutManager);


        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        container.setAdapter(adapter);

        //Set SnapHelper
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(container);

        //Init Service
        // this.spielerService = new MockService(false);
        this.spielerService = new RemoteSpielerService(this);
        this.busy = false;
        refreshContent();

    }

    /**
     * Holt sich eine Unbenutzte SpielID für das Neue Spiel Objekt und sammelt dann die ausgewählten Spieler.
     */
    private void refreshContent() {
        if (!busy) {
            this.busy = true;
            spielerService.fetchSpielList(
                    this::getFreeSpielId,
                    this::showErrorDialog
            );
            spielerService.fetchSpielerList(
                    this::addSpielercardToList,
                    this::showErrorDialog
            );
        }
    }

    private void showErrorDialog(Throwable throwable) {
    }

    private void addSpielercardToList(@NonNull List<Spieler> result) {
        this.busy = false;

        //Adapter säubern
        adapter.clear();
        // Spielkarte hinzufügen
        this.spiel = new Spiel(id);
        this.spielcardItem = new SpielcardItem(spiel);
        adapter.add(spielcardItem);

        //Spieler hinzufügen
        for (int c : spielerIds) {
            for (Spieler s : result) {
                if (s.getId() == c) {
                    adapter.add(new SpielercardItem(s, new SpielSpieler(s.getId(), id)));
                }
            }
        }

        //End-Card einfügen
        adapter.add(new EndcardItem().setOnEndListener(this));
    }

    @Override
    public void onEndButtonClick(@NonNull EndcardItem item) {
        try {
            if (busy) return;
            this.busy = true;
            // Einzelne Spieler ("stats")
            JSONArray stats = new JSONArray();

            for(ListItem c : adapter.getItems()) {
                if(c instanceof SpielerSubmitItem)
                    stats.put(((SpielerSubmitItem) c).getResult());
            }
            for(ListItem c: adapter.getItems()){
                if(c instanceof SpielercardItem){
                    ((SpielercardItem) c).getSpielSpieler().setSpielId(id);
                }
            }

            // Spiel-Objekt
            JSONObject jSpiel = new JSONObject()
                    .put("id", id)
                    .put("name", spiel.getTeamname())
                    .put("datum", DateConverter.DateToString(spiel.getDatum()))
                    .put("gegnerPunkte", spiel.getGastPunkte())
                    .put("unserePunkte", spiel.getHeimPunkte())
                    .put("stats", stats);

            //Ergebnisse übermittlen
            spielerService.submitSpiel(jSpiel, r -> backToMain(), this::handleCommonError);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getFreeSpielId(@NonNull List<Spiel> result) {
        id= 1;
        for (Spiel c : result) {
            if (c.getId() >= id) {
                id = c.getId();
                id++;
            }
        }

    }

    public void backToMain() {
        this.busy = false;
        finish();
    }

    /**
     * Zeige einen Fehler-Dialog für allgemeine Fehler (insbesondere bei der Übermittlung
     * der Ergebnisse).
     *
     * @param e Details.
     */
    private void handleCommonError(Throwable e) {
        this.busy = false;

        new AlertDialog.Builder(this, R.style.ErrorDialogTheme)
                .setTitle(R.string.dialog_title_error)
                .setMessage(String.format(Locale.getDefault(),
                        getString(R.string.dialog_message_error), e.getMessage()))
                .setPositiveButton(R.string.ok, null)
                .show();
    }
}
