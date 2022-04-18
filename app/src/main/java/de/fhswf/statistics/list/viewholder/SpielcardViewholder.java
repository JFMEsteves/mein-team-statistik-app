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

import java.util.Date;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.SpielcardItem;
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
//TODO 3 Unterschiedliche Textwatcher ?
    private void initTextWatcher(SpielcardItem item) {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
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
    private void initDatePicker(SpielcardItem item) {
        //Initialisiere OnDateSetListener
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month += 1;
            String date = makeDateString(dayOfMonth, month, year);
            Log.d(TAG, "onDateSet: " + date);
            dateButton.setText(date);
            item.setUserInput(date + ";");
        };
        datePickerDialog.setOnDateSetListener(dateSetListener);

    }


}
