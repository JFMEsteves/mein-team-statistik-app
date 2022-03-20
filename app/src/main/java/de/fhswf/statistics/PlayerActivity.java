package de.fhswf.statistics;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.list.ListAdapter;
import de.fhswf.statistics.list.fragments.GamesFragment;
import de.fhswf.statistics.list.fragments.SeasonFragment;
import de.fhswf.statistics.list.item.ListItem;
import de.fhswf.statistics.list.item.SpielSpielerListItem;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

public class PlayerActivity extends AppCompatActivity {
    public static final String EXTRA_SPIELER_ID = "id";

    private int SpielerID;
    private Spieler spieler;
    private SpielerService spielerService;
    private ListAdapter adapter;
    private boolean busy;
  private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // ID des Survey aus dem Intent entnehmen und in einem String speichern
        Intent mainActivityIntent = getIntent();
        SpielerID = Integer.parseInt(mainActivityIntent.getStringExtra(EXTRA_SPIELER_ID));

        tabLayout = findViewById(R.id.tabs);

        setContentView(tabLayout);

        adapter = new ListAdapter();
// Fragments Teil für Tablayout
        //TODO Tablayout fertigstellen
        adapter.addFragment(new SeasonFragment());
        adapter.addFragment(new GamesFragment());
    }
    /**
     * Stößt das Abrufen der Survey-Details (mit Fragen) an.
     */
    private void fetchSpielerDetails() {
        if (!busy) {
            this.busy = true;

            spielerService.fetchSpielerDetails(
                    SpielerID,
                    this::handleSpielerResult,
                    this::handleSpielerDetailsError
            );
        }
    }
    /**
     * Aufgerufen, wenn der SurveyService die Survey erfolgreich abgerufen hat.
     *
     * @param result Survey mit Details.
     */
    private void handleSpielerResult(Spieler result) {
        this.busy = false;
        this.spieler = result;

        if (spieler.getStats() == null) {
            handleSpielerDetailsError(new RuntimeException("Spielerdaten konnten nicht gefunden werden!"));
            return;
        }

        // Titel der Activity
        setTitle(spieler.getName());
        adapter.clear();
// TODO Test and fix ?
        for (SpielSpieler c : spieler.getStats()) {
            ListItem item;
            item = new SpielSpielerListItem((SpielSpieler) c);

            adapter.add(item);
        }
    }
    /**
     * Fehler-Behandlung für das Abrufen der Survey-Details.
     * <p>
     * Da ein Versagen hier bedeutet, dass die Activity nicht weiter genutzt werden kann, gibt
     * es hier lediglich die Möglichkeit, es erneut zu versuchen, oder die Activity zu beenden.
     *
     * @param e Fehler-Details.
     */
    private void handleSpielerDetailsError(Throwable e) {
        this.busy = false;

        new AlertDialog.Builder(this, R.style.ErrorDialogTheme)
                .setTitle(R.string.dialog_title_error)
                .setMessage(String.format(Locale.getDefault(),
                        getString(R.string.dialog_message_error), e.getMessage()))
                .setPositiveButton(R.string.retry, (dialog, which) -> fetchSpielerDetails())
                .setNegativeButton(R.string.exit, (dialog, which) -> finish())
                .setCancelable(true).setOnCancelListener(di -> finish())
                .show();
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
