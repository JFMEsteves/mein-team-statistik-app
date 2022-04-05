package de.fhswf.statistics.list.item;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public interface PlayerSubmitItem {
    @Nullable
    JSONObject getResult() throws JSONException;
}
