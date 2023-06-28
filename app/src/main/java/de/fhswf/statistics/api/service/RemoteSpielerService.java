package de.fhswf.statistics.api.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.fhswf.statistics.api.OnFailureListener;
import de.fhswf.statistics.api.OnSuccessListener;
import de.fhswf.statistics.api.parser.SpielListParser;
import de.fhswf.statistics.api.parser.SpielParser;
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
    public static final String BASE_URL = "http://213.136.76.136:9080/api";

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
    public void fetchSpielDetails(int id,
                                  @Nullable OnSuccessListener<Spiel> onSuccessListener,
                                  @Nullable OnFailureListener onFailureListener) {
        try {
            new RemoteRequest<>(
                    context,
                    BASE_URL + "/spiel/details",
                    new JSONObject().put("id", id),
                    new SpielParser(),
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

        new RemoteRequest<>(
                context,
                BASE_URL + "/spiel/list",
                new JSONObject(),
                new SpielListParser(),
                onSuccessListener,
                onFailureListener
        ).execute();
    }

    @Override
    public void submitSpiel(@NonNull JSONObject content,
                            @Nullable OnSuccessListener<Void> onSuccessListener,
                            @Nullable OnFailureListener onFailureListener) {
        try {
            new RemoteRequest<>(
                    context,
                    BASE_URL + "/spiel/add",
                    new JSONObject()
                            .put("spiel", content),
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
