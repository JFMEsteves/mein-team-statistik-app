package de.fhswf.statistics;

import static android.content.ContentValues.TAG;
import static de.fhswf.statistics.util.StatCalculator.makeDateString;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.list.ListAdapter;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.model.Spieler;
import de.fhswf.statistics.util.StatCalculator;

public class MainActivity extends AppCompatActivity implements SpielerListItem.OnSpielerListener {
    private PopupWindow newGamePopupWindow;
    private ListAdapter adapter;
    private Context context;
    private DatePickerDialog datePickerDialog;
    private StatCalculator calculator;

    private Button dateButton, closeButton, createButton, playerButton;
    //TODO Refactor between activites of SpielerService
    //TODO Finish Popup Window
    private SpielerService SpielService;
    private boolean busy;
    private ConstraintLayout constraintLayout;
    private ArrayList<Spieler> chosenplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.constraint_container);


        RecyclerView container = findViewById(R.id.container);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        container.setLayoutManager(layoutManager);

        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        container.setAdapter(adapter);


        //Add Game Button
        FloatingActionButton addGame = findViewById(R.id.addGameBtn);
        addGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // LayoutInflater.from(v.getContext());
                View customView = inflater.inflate(R.layout.new_game_popup, null);


                //instanzierung Popup Window
                newGamePopupWindow = new PopupWindow(customView,
                        RecyclerView.LayoutParams.WRAP_CONTENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT);

                //Intialisierung Buttons des PopupWindows
                dateButton = (Button) customView.findViewById(R.id.date_button);
                closeButton = customView.findViewById(R.id.close_button);
                createButton = customView.findViewById(R.id.create_button);
                playerButton = customView.findViewById(R.id.choose_player_button);

                // Show Popup
                newGamePopupWindow.showAtLocation(constraintLayout, Gravity.CENTER, 0, 0);
                dateButton.setText(calculator.getTodaysDate());

                // initialisiere DatePickerDialog
                initDatePicker();

                //Button Listeners
                dateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePickerDialog.show();
                    }
                });

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newGamePopupWindow.dismiss();
                    }
                });

                playerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO Create
                    }
                });

                createButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO Hier muss Liste von Spielern übergeben werden
                        Intent intent = new Intent(v.getContext(), newGameActivity.class);
                        //startActivity(intent);
                        v.getContext().startActivity(intent);
                        //TODO create Intent for next Activity that contains List of Players and transfers Gamedata
                        //TODO Check for existing Gamedata and throw exception if already existing
                    }
                });


            }
        });

        // init Service
        this.SpielService = new MockService(false);
        // Daten von Service laden
        this.busy = false;
        refreshContent();
    }

    /**
     * Initialisiere DatePicker Listener; Ändere den Text des Buttons zum ausgewählten Datum, Setze den Listener
     */
    private void initDatePicker() {
        //Initialisiere OnDateSetListener
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                String date = makeDateString(dayOfMonth, month, year);
                Log.d(TAG, "onDateSet: " + date);
                dateButton.setText(date);
            }
        };
        datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener(dateSetListener);

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
     * Kapselt die erhaltenen Spieler und fügt sie dem Adapter
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
        //TODO finde Lösung für dieses Problem
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