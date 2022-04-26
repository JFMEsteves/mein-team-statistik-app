package de.fhswf.statistics.api.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


import de.fhswf.statistics.api.OnFailureListener;
import de.fhswf.statistics.api.OnSuccessListener;
import de.fhswf.statistics.api.parser.SpielerListParser;
import de.fhswf.statistics.api.parser.SpielerParser;
import de.fhswf.statistics.api.remote.RemoteRequest;
import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.Spieler;

/**
 * Service-Implementierung für die Live-API über HTTP.
 *
 * @see RemoteRequest Für HTTP-Anfragen verwendet.
 */
public class RemoteSpielerService implements SpielerService {

    /**
     * Basis-URL des Remote-Backends (ohne abschließendes '/').
     */
    public static final String BASE_URL = "http://167.86.108.121:9084/SurveyBackend/api";

    private final @NonNull
    Context context;

    /**
     * Erzeuge eine neue Service-Instanz.
     *
     * @param context Context, erforderlich für Requests.
     */
    public RemoteSpielerService(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void fetchSpielerList(@Nullable OnSuccessListener<List<Spieler>> onSuccessListener,
                                 @Nullable OnFailureListener onFailureListener) {
        new RemoteRequest<>(
                context,
                BASE_URL + "/spieler/list",
                new JSONObject(),
                new SpielerListParser(),
                onSuccessListener,
                onFailureListener
        ).execute();
    }

    @Override
    public void fetchSpielerDetails(int id,
                                    @Nullable OnSuccessListener<Spieler> onSuccessListener,
                                    @Nullable OnFailureListener onFailureListener) {
        try {
            new RemoteRequest<>(
                    context,
                    BASE_URL + "/spieler/details",
                    new JSONObject().put("id", id),
                    new SpielerParser(),
                    onSuccessListener,
                    onFailureListener
            ).execute();
        } catch (JSONException e) {
            if (onFailureListener != null)
                onFailureListener.onFailure(e);
        }
    }

    @Override
    public void fetchSpielList(@Nullable OnSuccessListener<List<Spiel>> onSuccessListener, @Nullable OnFailureListener onFailureListener) {

    }

    @Override
    public void submitSpiel( int id, @NonNull JSONArray results,
                            @Nullable OnSuccessListener<Void> onSuccessListener,
                            @Nullable OnFailureListener onFailureListener) {
        try {
            new RemoteRequest<>(
                    context,
                    BASE_URL + "/spiel/submit",
                    new JSONObject()
                            .put("id", id)
                            .put("spiel", results),
                    null,
                    onSuccessListener,
                    onFailureListener
            ).execute();
        } catch (JSONException e) {
            if (onFailureListener != null)
                onFailureListener.onFailure(e);
        }
    }


}
