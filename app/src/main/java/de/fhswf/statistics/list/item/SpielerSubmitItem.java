package de.fhswf.statistics.list.item;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Interface um Eingaben des Nutzers als JSON Objekte abzufragen.
 */
public interface SpielerSubmitItem {
    /**
     * Generiert ein Antwort-Objekt nach API-Spezifikation.
     *
     * @return JSON-Objekt mit allen Antwort-Daten, oder <pre>null</pre>, wenn für die entsprechende
     * Frage keine Eingaben vorliegen.
     * @throws JSONException Geworfen, wenn das Zusammenbauen des JSON-Objekts fehlschlägt.
     */
    @Nullable
    JSONObject getResult() throws JSONException;
}
