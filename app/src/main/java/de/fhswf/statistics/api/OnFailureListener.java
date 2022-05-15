package de.fhswf.statistics.api;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

/**
 * Einfaches Interface, das für negative Service-Callbacks genutzt wird.
 *
 * (Es sollte immer sichergestellt werden, dass der Aufruf der {@link this#onFailure(Throwable)}
 * Methode immer im Main-Thread stattfindet.)
 */
public interface OnFailureListener {

    /**
     * Wird aufgerufen, wenn während der Service-Anfrage ein Fehler aufgetreten ist.
     *
     * @param e Fehlerursache mit zusätzlichen Informationen.
     */
    @MainThread
    void onFailure(@NonNull Throwable e);
}
