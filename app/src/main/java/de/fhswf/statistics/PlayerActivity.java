package de.fhswf.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

import de.fhswf.statistics.api.service.RemoteSpielerService;
import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.list.Adapter.SectionsPagerAdapter;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.ListItem;
import de.fhswf.statistics.list.item.SpielSpielerListItem;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

public class PlayerActivity extends AppCompatActivity {
    public static final String EXTRA_SPIELER_ID = "id";

    private int spielerID;
    private Spieler spieler;
    private SpielerService spielerService;
    private boolean busy;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_tablayout);


        // ID des Survey aus dem Intent entnehmen und in einem String speichern
        Intent mainActivityIntent = getIntent();
        spielerID = mainActivityIntent.getIntExtra(EXTRA_SPIELER_ID, -1);


        //Init Service
        //  this.spielerService = new MockService(false);
        this.spielerService = new RemoteSpielerService(this);
        this.busy = false;
        fetchSpielerDetails();


    }

    /**
     * ViewPager vorbereiten
     */
    private void preparePager() {
        setTitle(spieler.getName());
        SectionsPagerAdapter sectionsPagerAdapter =
                new SectionsPagerAdapter(this, getSupportFragmentManager(), spieler);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);

        // VP Tabs
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Stößt das Abrufen der Spieler an.
     */
    private void fetchSpielerDetails() {
        if (!busy) {
            this.busy = true;

            spielerService.fetchSpielerDetails(
                    spielerID,
                    this::handleSpielerResult,
                    this::handleSpielerDetailsError
            );
        }
    }

    /**
     * Aufgerufen, wenn der SpielerService die Survey erfolgreich abgerufen hat.
     *
     * @param result Spieler
     */
    private void handleSpielerResult(Spieler result) {
        this.busy = false;
        this.spieler = result;

        if (spieler.getStats() == null) {
            handleSpielerDetailsError(new RuntimeException("Spielerdaten konnten nicht gefunden werden!"));
            return;
        }

        preparePager();
    }

    /**
     * Fehler-Behandlung für das Abrufen der Spieler-Details.
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
