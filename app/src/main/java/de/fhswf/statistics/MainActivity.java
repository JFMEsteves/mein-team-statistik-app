package de.fhswf.statistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupWindow;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.dialog.SpielerAuswahlDialog;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.model.Spieler;
import de.fhswf.statistics.util.StatCalculator;

public class MainActivity extends AppCompatActivity implements SpielerListItem.OnSpielerListener {

    private ListAdapter adapter;
    private Context context;
    private StatCalculator calculator;

    private Button dateButton, closeButton, createButton, playerButton;
    //TODO Refactor between activites of SpielerService
    private SpielerService SpielService;
    private boolean busy;
    private ConstraintLayout constraintLayout;
    private ArrayList<Spieler> chosenplayer,Allplayers;
    private List<Spieler> testing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraint_container);
        Allplayers = new ArrayList<>();
        this.chosenplayer = new ArrayList<>();


        RecyclerView container = findViewById(R.id.container);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        container.setLayoutManager(layoutManager);

        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        container.setAdapter(adapter);


        //Add Game Button
        FloatingActionButton addGame = findViewById(R.id.addGameBtn);
        addGame.setOnClickListener(v -> {
            new SpielerAuswahlDialog(this, Allplayers);
        });

        // init Service
        this.SpielService = new MockService(false);
        // Daten von Service laden
        this.busy = false;
        refreshContent();

    }


    private void refreshContent() {
        if (!busy) {
            this.busy = true;
            SpielService.fetchSpielerList(
                    this::addSpielerToList,
                    this::showErrorDialog
            );

        }
    }

    /**
     * Kapselt die erhaltenen Spieler und fügt sie dem Adapter
     * hinzu.
     *
     * @param result Ergebnis des Services.
     */
    private void addSpielerToList(List<Spieler> result) {
        this.busy = false;
        adapter.clear();

            for (Spieler c : result) {
                Allplayers.add(c);
                adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
            }
    }

    @Override
    public void onSpielerClick(@NonNull SpielerListItem item) {
        //Intent zu Player Activity, TO BE BUILT
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(PlayerActivity.EXTRA_SPIELER_ID, item.getSpieler().getId());
        startActivity(intent);
    }

    /**
     * Zeigt einen Fehler-Dialog an.
     * <p>
     * Da die Anwendung bei einem Service-Fehler nutzlos ist, gibt es entweder die Option, es
     * direkt erneut zu versuchen, oder die App wird beendet.
     *
     * @param e Fehler-Details.
     */
    private void showErrorDialog(Throwable e) {
        this.busy = false;

        new AlertDialog.Builder(this, R.style.ErrorDialogTheme)
                .setTitle(R.string.dialog_title_error)
                .setMessage(String.format(
                        getString(R.string.dialog_message_error), e.getMessage()))
                .setPositiveButton(R.string.retry, (dialog, which) -> refreshContent())
                .setNegativeButton(R.string.exit, (dialog, which) -> finish())
                .setCancelable(true)
                .setOnCancelListener(dialog -> finish())
                .show();
    }

}