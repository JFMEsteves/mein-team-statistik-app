package de.fhswf.statistics.list.viewholder;

import static android.content.ContentValues.TAG;
import static de.fhswf.statistics.util.StatCalculator.makeDateString;

import android.app.DatePickerDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.util.Date;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielcardItem;
import de.fhswf.statistics.util.DateConverter;
import de.fhswf.statistics.util.StatCalculator;

public class SpielcardViewholder extends BaseViewHolder<SpielcardItem> {

    @NonNull
    private DatePickerDialog datePickerDialog;
    @NonNull
    private final Button dateButton;
    @NonNull
    private final EditText nameInput,myteamInput,enemyteamInput;

    public SpielcardViewholder(@NonNull View itemView) {
        super(itemView);
        this.dateButton = itemView.findViewById(R.id.date_button);
        this.nameInput = itemView.findViewById(R.id.name_input);
        this.myteamInput = itemView.findViewById(R.id.myteam_input);
        this.enemyteamInput = itemView.findViewById(R.id.enemy_input);
        datePickerDialog = new DatePickerDialog(itemView.getContext());
    }


    @Override
    public void bind(SpielcardItem item) {
        initDatePicker(item);
        dateButton.setText(StatCalculator.getTodaysDate());
        dateButton.setOnClickListener(v -> datePickerDialog.show());

    }
//TODO 3 Unterschiedliche Textwatcher ? Besseres Coding ?
    private void initTextWatcher(SpielcardItem item) {
        TextWatcher textWatcherName = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.getSpiel().setTeamname(s.toString());
            }
        };
        TextWatcher textWatcherEnemyInput = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.getSpiel().setGastPunkte(Integer.parseInt(s.toString()));
            }
        };
        TextWatcher textWatcherUnserTeamInput = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.getSpiel().setHeimPunkte(Integer.parseInt(s.toString()));
            }
        };
        nameInput.addTextChangedListener(textWatcherName);
        enemyteamInput.addTextChangedListener(textWatcherEnemyInput);
        myteamInput.addTextChangedListener(textWatcherUnserTeamInput);

    }










    /**
     * Initialisiere DatePicker Listener; Ändere den Text des Buttons zum ausgewählten Datum, Setze den Listener
     */
    private void initDatePicker(SpielcardItem item) {
        //Initialisiere OnDateSetListener
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month += 1;
            String date = makeDateString(dayOfMonth, month, year);
            dateButton.setText(date);
            Date datum = null;
            try {
                datum = DateConverter.StringtoDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(datum != null) {
                item.getSpiel().setDatum(datum);
            }
        };
        datePickerDialog.setOnDateSetListener(dateSetListener);

    }


}
