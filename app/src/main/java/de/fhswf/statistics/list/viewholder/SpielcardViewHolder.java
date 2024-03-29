package de.fhswf.statistics.list.viewholder;

import static de.fhswf.statistics.util.StatCalculator.makeDateString;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.util.Date;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielcardItem;
import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.util.DateConverter;
import de.fhswf.statistics.util.SimpleUpdateTextWatcher;

/**
 * Viewholder von @link {@link SpielcardItem}
 */
public class SpielcardViewHolder extends BaseViewHolder<SpielcardItem> {


    private final DatePickerDialog datePickerDialog;

    private final Button dateButton;

    private final EditText nameInput, myteamInput, enemyteamInput;

    public SpielcardViewHolder(@NonNull View itemView) {
        super(itemView);
        this.dateButton = itemView.findViewById(R.id.date_button);
        this.nameInput = itemView.findViewById(R.id.name_input);
        this.myteamInput = itemView.findViewById(R.id.myteamInput);
        this.enemyteamInput = itemView.findViewById(R.id.enemyInput);
        datePickerDialog = new DatePickerDialog(itemView.getContext());
    }


    @Override
    public void bind(SpielcardItem item) {
        initDatePicker(item);
        initTextWatcher(item);
        // dateButton.setText(StatCalculator.getTodaysDate());
        dateButton.setOnClickListener(v -> datePickerDialog.show());

    }

    /**
     * initialisiert die nötigen TextWatcher und füllt das item bei Eingaben über diesen
     *
     * @param item {@link Spiel}
     */
    private void initTextWatcher(SpielcardItem item) {

        /*
         * Die Folgenden ifs sind zur "Wiederherstellung" der Nutzereingaben. Da eine Recyclerview nicht
         * alle children gleichzeitig persistiert müssen daten zugewiesen werden wenn sie erneut persistiert
         * werden nachdem man dort schon etwas eingegeben hat.
         */
        if (item.getSpiel().getTeamname() != null) nameInput.setText(item.getSpiel().getTeamname());
        else if (item.getSpiel().getTeamname() == null) nameInput.setText(null);
        nameInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> item.getSpiel().setTeamname(t)));


        if (item.getSpiel().getGastPunkte() != 0)
            enemyteamInput.setText(String.valueOf(item.getSpiel().getGastPunkte()));
        else if (item.getSpiel().getGastPunkte() == 0) enemyteamInput.setText(null);
        enemyteamInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty()) item.getSpiel().setGastPunkte(Integer.parseInt(t));
                }));

        if (item.getSpiel().getHeimPunkte() != 0)
            myteamInput.setText(String.valueOf(item.getSpiel().getHeimPunkte()));
        else if (item.getSpiel().getHeimPunkte() == 0) myteamInput.setText(null);

        myteamInput.addTextChangedListener(new SimpleUpdateTextWatcher(
                t -> {
                    if (!t.isEmpty()) item.getSpiel().setHeimPunkte(Integer.parseInt(t));
                }));

    }


    /**
     * Initialisiere DatePicker Listener; Ändere den Text des Buttons zum ausgewählten Datum, Setze den Listener
     */
    private void initDatePicker(SpielcardItem item) {
        //Initialisiere OnDateSetListener
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month += 1;
            String date = makeDateString(dayOfMonth, month, year);
            //Nutzereingabe "wiederherstellen"
            if (item.getSpiel().getDatum() != null) {
                dateButton.setText(DateConverter.DateToString(item.getSpiel().getDatum()));
            }
            Date datum = null;
            try {
                datum = DateConverter.StringtoDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (datum != null) {
                item.getSpiel().setDatum(datum);
                dateButton.setText(date);
            }
        };
        //Setze Listener
        datePickerDialog.setOnDateSetListener(dateSetListener);

    }


}
