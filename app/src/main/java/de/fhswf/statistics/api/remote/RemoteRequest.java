package de.fhswf.statistics.api.remote;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import de.fhswf.statistics.api.OnFailureListener;
import de.fhswf.statistics.api.OnSuccessListener;
import de.fhswf.statistics.api.parser.ResponseParser;

/**
 * Werkzeug, um per Volley eine HTTP-Anfrage an die API einzuleiten.
 * <p>
 * Die Anfrage-Argumente werden gemäß der API-Spezifikation als {@link JSONObject}
 * entgegengenommen. Es ist möglich, eine Anfrage mit leerem Body einzuleiten (null).
 * <p>
 * War die Anfrage erfolgreich, dann wird die äußere Antwort der API gemäß der Spezifikation
 * von dieser Klasse interpretiert (ok, error_message).
 * <p>
 * Ist die Antwort von der API ebenfalls positiv, dann wird der Inhalt des 'data' Feldes mit
 * einem {@link ResponseParser} verarbeitet und das Ergebnis zuletzt über die
 * {@link OnSuccessListener}-Instanz weitergegeben.
 * <p>
 * Alle Fehler, die in diesem Prozess auftreten können (Anfrage/Netzwerk, API-Fehler, korrupte
 * oder andererseits nicht interpretierbare Daten) werden hier gefangen und einheitlich
 * über das {@link OnFailureListener}-Interface an den Caller weitergegeben.
 *
 * @param <T> Antwort-Typ der Anfrage.
 */
public class RemoteRequest<T> {

    private final @NonNull
    Context context;

    private final @NonNull
    String url;

    private final @Nullable
    JSONObject body;

    private final @Nullable
    ResponseParser<T> parser;

    private final @Nullable
    OnSuccessListener<T> onSuccessListener;

    private final @Nullable
    OnFailureListener onFailureListener;

    private boolean executed = false;
    private final Handler handler = new Handler(Looper.getMainLooper());

    /**
     * Erstelle eine neue Anfrage.
     *
     * @param context           Context, zwingend erforderlich.
     * @param url               Ziel-URL, erforderlich.
     * @param body              Request-Body als JSON-Object, optional.
     * @param parser            Parser für die Antwort, erforderlich.
     * @param onSuccessListener Ergebnis-Callback, typisiert, optional.
     * @param onFailureListener Fehler-Callback, optional.
     */
    public RemoteRequest(@NonNull Context context,
                         @NonNull String url,
                         @Nullable JSONObject body,
                         @Nullable ResponseParser<T> parser,
                         @Nullable OnSuccessListener<T> onSuccessListener,
                         @Nullable OnFailureListener onFailureListener) {
        this.context = context;
        this.url = url;
        this.body = body;
        this.parser = parser;
        this.onSuccessListener = onSuccessListener;
        this.onFailureListener = onFailureListener;
    }

    /**
     * Führe die Anfrage aus.
     * <p>
     * Es wird sichergestellt, dass jede individuelle Anfrage nur ein mal ausgeführt wird, um
     * mehrfache Aufrufe der Ergebnis- bzw. Fehler-Callbacks zu verhindern.
     */
    public void execute() {
        // Sicherstellen, dass jede individuelle Anfrage nur ein einziges Mal in die
        // Warteschlange eingefügt wird
        if (executed)
            return;

        this.executed = true;

        // Volley-Request bauen
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, url, body,
                this::handleResult,
                this::handleVolleyError
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Eigene Header, um das Backend auf den Content-Type und das verwendete
                // Charset hinzuweisen. Erforderlich, damit Anfragen mit Umlauten und anderen
                // Sonderzeichen erfolgreich ausgeführt werden können.

                Map<String, String> headers = new HashMap<>(2);
                headers.put("Content-Type", "application/json");
                headers.put("charset", "utf-8");

                return headers;
            }
        };

        // Request in Warteschlange einreihen
        de.fhswf.statistics.api.remote.RequestManager.getInstance(context).addToRequestQueue(request);
    }

    /**
     * Verarbeitet die Antwort der API.
     * <p>
     * War der API-Aufruf erfolgreich, dann wird versucht, das Daten-Feld mit dem bereitgestellten
     * Parser zu verarbeiten.
     *
     * @param response API-Antwort als JSON-Object.
     */
    private void handleResult(JSONObject response) {
        if (response.optBoolean("ok", false)) {
            if (parser == null) {
                // Wenn die gegebene Parser-Instanz null ist, dann wird davon ausgegangen, dass
                // die Antwort nicht relevant ist (Verwendung mit Void als Typen)
                postSuccess(null);
            } else {
                // Datenverarbeitung in neuem Thread
                new ParseDataTask(response).start();
            }
        } else {
            // API-Antwort war nicht erfolgreich. Versuche, das Fehler-Callback mit der
            // Fehlermeldung von der API aufzurufen ('error_message'-Feld). Sollte das Feld auch
            // fehlen, dann wird ein generischer Text als Fallback eingesetzt.
            postFailure(new ApiException(response.optString(
                    "error_message", "Unknown API-error.")));
        }
    }

    /**
     * Behandelt Volley-Fehler (keine Verbindung, HTTP-Fehler, etc.).
     * <p>
     * Diese werden vor dem Weitergeben in einer API-Exception umschlossen, um die Message
     * benutzerfreundlicher zu halten.
     *
     * @param error Volley Fehler.
     */
    private void handleVolleyError(VolleyError error) {
        postFailure(new ApiException("Network error.", error));
    }

    /**
     * Ruft das Fehler-Callback im Main-Thread auf.
     *
     * @param e Fehler-Details.
     */
    private void postFailure(@NonNull Throwable e) {
        if (onFailureListener != null) {
            handler.post(() -> onFailureListener.onFailure(e));
        }
    }

    /**
     * Ruft das Ergebnis-Callback im Main-Thread auf.
     *
     * @param result Ergebnis-Objekt.
     */
    private void postSuccess(@Nullable T result) {
        if (onSuccessListener != null) {
            handler.post(() -> onSuccessListener.onSuccess(result));
        }
    }

    /**
     * Thread-Klasse, welche die empfangenden Daten asynchron verarbeitet.
     */
    private class ParseDataTask extends Thread {

        private final JSONObject response;

        public ParseDataTask(JSONObject response) {
            this.response = response;
            setName("ResultParserThread");
        }

        @Override
        public void run() {
            try {
                if (parser == null)
                    throw new IllegalStateException("Parser must not be null!");

                // Gebe das 'data'-Feld an den Parser weiter
                T result = parser.parse(response.getJSONObject("data"));

                // Veröffentliche das Ergebnis
                postSuccess(result);
            } catch (Exception e) {
                postFailure(e);
            }
        }
    }
}
