package de.fhswf.statistics.list.item;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
//TODO In SpielercardItem & SpielListItem, UserInput datentypen individuell anpassen
public interface SpielerSubmitItem {
    @Nullable
    JSONObject getResult() throws JSONException;
}
