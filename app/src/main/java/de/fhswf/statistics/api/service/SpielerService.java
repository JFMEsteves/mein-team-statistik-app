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
    void fetchSpielerList(@Nullable OnSuccessListener<List<Spieler>> onSuccessListener,
                               @Nullable OnFailureListener onFailureListener);

    void fetchSpielerDetails(int id,
            @Nullable OnSuccessListener<Spieler> onSuccessListener,
                                  @Nullable OnFailureListener onFailureListener);
    void fetchSpielList(@Nullable OnSuccessListener<List<Spiel>> onSuccessListener, @Nullable OnFailureListener  onFailureListener);

    void submitSpiel(@NonNull JSONObject content,
                     @Nullable OnSuccessListener<Void> onSuccessListener,
                     @Nullable OnFailureListener onFailureListener);
}
