package de.fhswf.statistics.api.parser;

import androidx.annotation.NonNull;

import org.json.JSONObject;

/**
 * Ein Interface, das einen einfachen Response-Parser beschreibt, welcher gegebene Daten im
 * JSON-Format interpretiert und in den geforderten Typen überführt.
 *
 * @param <T> Ergebnis Objekt-Typ.
 */
public interface ResponseParser<T> {

    /**
     * Verarbeite die JSON-Daten zu einer Instanz der Ziel-Objekt-Klasse.
     *
     * @param data Quelldaten.
     * @return Instanz der Ergebnis-Klasse.
     * @throws ParsingException Geworfen, wenn beim Verarbeiten ein Fehler auftritt.
     */
    @NonNull
    T parse(@NonNull JSONObject data) throws ParsingException;

}
