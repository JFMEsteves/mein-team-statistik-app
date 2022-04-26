package de.fhswf.statistics.api.remote;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Singleton-Klasse, welche eine Request-Queue von {@link Volley} verwaltet.
 */
public class RequestManager {

    @SuppressLint("StaticFieldLeak")
    private static RequestManager instance;

    private final @NonNull
    Context context;

    private RequestQueue requestQueue;

    private RequestManager(@NonNull Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

    public <T> void addToRequestQueue(@NonNull Request<T> request) {
        getRequestQueue().add(request);
    }

    public @NonNull
    RequestQueue getRequestQueue() {
        if (this.requestQueue == null) {
            this.requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public static RequestManager getInstance(@NonNull Context context) {
        if (instance == null) {
            RequestManager.instance = new RequestManager(context);
        }

        return instance;
    }
}
