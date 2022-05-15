package de.fhswf.statistics.util;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;

/**
 * Auslagerung zur Reduzierung von redundantem Code
 */
public class SimpleUpdateTextWatcher implements TextWatcher {

    private final @NonNull
    OnTextUpdateListener onTextUpdateListener;

    public SimpleUpdateTextWatcher(@NonNull OnTextUpdateListener onTextUpdateListener) {
        this.onTextUpdateListener = onTextUpdateListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        onTextUpdateListener.onTextUpdated(s.toString());
    }

    public interface OnTextUpdateListener {
        void onTextUpdated(String text);
    }

}
