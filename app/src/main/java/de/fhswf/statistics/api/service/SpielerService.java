package de.fhswf.statistics.api.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.util.List;

import de.fhswf.statistics.api.OnFailureListener;
import de.fhswf.statistics.api.OnSuccessListener;
import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.Spieler;

public interface SpielerService {
    /**
     * Frage die Liste aller Spieler ab (mit ihren Statistiken)
     *
     * @param onSuccessListener Erfolg-Callback, enthält eine Liste von Spieler-Objekten.
     * @param onFailureListener Fehler-Callback
     */
    void fetchSpielerList(@Nullable OnSuccessListener<List<Spieler>> onSuccessListener,
                          @Nullable OnFailureListener onFailureListener);

    /**
     * Frage die Details (alle Statistiken) eines bestimmten Spielers ab.
     *
     * @param id                ID des abzufragenden Spielers.
     * @param onSuccessListener Erfolg-Callback, enthält ein einzelnes Spieler-Objekt.
     * @param onFailureListener Fehler-Callback
     */
    void fetchSpielerDetails(int id,
                             @Nullable OnSuccessListener<Spieler> onSuccessListener,
                             @Nullable OnFailureListener onFailureListener);

    /**
     * Frage die Liste aller Spiele ab (mit ihren Statistiken)
     *
     * @param onSuccessListener Erfolg-Callback, enthält eine Liste von Spieler-Objekten.
     * @param onFailureListener Fehler-Callback
     */
    void fetchSpielList(@Nullable OnSuccessListener<List<Spiel>> onSuccessListener, @Nullable OnFailureListener onFailureListener);

    /**
     * Sende die Nutzer-Eingaben als neues Spiel Objekt.
     *
     * @param content           Spiel-Objekt als JSON Object
     * @param onSuccessListener Antwort-Daten als JSON-Objekt
     * @param onFailureListener Fehler-Callback
     */
    void submitSpiel(@NonNull JSONObject content,
                     @Nullable OnSuccessListener<Void> onSuccessListener,
                     @Nullable OnFailureListener onFailureListener);
}
