package de.fhswf.statistics.api.service;

import androidx.annotation.Nullable;

import java.util.List;

import de.fhswf.statistics.api.OnFailureListener;
import de.fhswf.statistics.api.OnSuccessListener;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

public interface SpielerService {
    void fetchSpielerList(@Nullable OnSuccessListener<List<Spieler>> onSuccessListener,
                               @Nullable OnFailureListener onFailureListener);

    void fetchSpielerDetails(int id,
            @Nullable OnSuccessListener<Spieler> onSuccessListener,
                                  @Nullable OnFailureListener onFailureListener);
}
