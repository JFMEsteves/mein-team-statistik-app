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

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielListItem;
import de.fhswf.statistics.list.item.SpielercardItem;
import de.fhswf.statistics.util.StatCalculator;

public class SpielViewholder extends BaseViewHolder<SpielListItem> {

    private DatePickerDialog datePickerDialog;
    private final Button dateButton;
    private final EditText nameInput,myteamInput,enemyteamInput;

    public SpielViewholder(@NonNull View itemView) {
        super(itemView);
        this.dateButton = itemView.findViewById(R.id.date_button);
        this.nameInput = itemView.findViewById(R.id.name_input);
        this.myteamInput = itemView.findViewById(R.id.myteam_input);
        this.enemyteamInput = itemView.findViewById(R.id.enemy_input);
        datePickerDialog = new DatePickerDialog(itemView.getContext());
    }


    @Override
    public void bind(SpielListItem item) {
        initDatePicker(item);
        dateButton.setText(StatCalculator.getTodaysDate());
        dateButton.setOnClickListener(v -> {
            datePickerDialog.show();
        });

    }

    private void initTextWatcher(SpielListItem item) {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override // Delimter ; um daten nachher zu trennen
            public void afterTextChanged(Editable s) {
                item.setUserInput(s.toString() + ";");
            }
        };
        nameInput.addTextChangedListener(textWatcher);
        enemyteamInput.addTextChangedListener(textWatcher);
        myteamInput.addTextChangedListener(textWatcher);

    }










    /**
     * Initialisiere DatePicker Listener; Ändere den Text des Buttons zum ausgewählten Datum, Setze den Listener
     */
    private void initDatePicker(SpielListItem item) {
        //Initialisiere OnDateSetListener
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                String date = makeDateString(dayOfMonth, month, year);
                Log.d(TAG, "onDateSet: " + date);
                dateButton.setText(date);
                item.setUserInput(date + ";");
            }
        };
        datePickerDialog.setOnDateSetListener(dateSetListener);

    }


}
