package de.fhswf.statistics.api;

import androidx.annotation.MainThread;

/**
 * Einfaches Interface, das für positive Service-Callbacks genutzt wird und ein Ergebnis-Objekt
 * enthält.
 * <p>
 * (Es sollte immer sichergestellt werden, dass der Aufruf der {@link this#onSuccess(Object)}
 * Methode immer im Main-Thread stattfindet.)
 *
 * @param <T> Typ des Ergebnis-Objekts.
 */
public interface OnSuccessListener<T> {

    /**
     * Wird aufgerufen, wenn die Service-Anfrage erfolgreich ausgeführt wurde.
     *
     * @param result Das Ergebnis-Objekt der Anfrage.
     */
    @MainThread
    void onSuccess(T result);
}
