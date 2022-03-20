package de.fhswf.statistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.list.ListAdapter;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.model.Spieler;

public class MainActivity extends AppCompatActivity implements SpielerListItem.OnSpielerListener {

    private ListAdapter adapter;
//TODO Refactor between activites of SpielerService
    private SpielerService SpielService;
    private boolean busy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView container = findViewById(R.id.container);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        container.setLayoutManager(layoutManager);

        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        container.setAdapter(adapter);

        // init Service
        this.SpielService = new MockService(false);
        // Daten von Service laden
        this.busy = false;
        refreshContent();
    }
    /**
     * Initiiert das Abrufen von Umfragen.
     */
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
     * Kapselt die erhaltenen Umfragen in {@link } und fügt sie dem Adapter
     * hinzu.
     *
     * @param result Ergebnis des Services.
     */
    private void addSpielerToList(List<Spieler> result) {
        this.busy = false;
        adapter.clear();

        for (Spieler c : result) {
            adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
        }
    }

    @Override
    public void onSpielerClick(@NonNull SpielerListItem item) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(String.valueOf(PlayerActivity.EXTRA_SPIELER_ID), item.getSpieler().getId());
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