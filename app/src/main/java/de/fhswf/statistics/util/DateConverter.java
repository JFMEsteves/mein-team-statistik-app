package de.fhswf.statistics.util;

import androidx.annotation.NonNull;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Einfacher LocalDate Konverter um LocalDate Daten zu Strings zu machen um in JSON Pakete zu packen oder vice versa.
 * Dabei eine bestimmte Pattern einhalten dd.MM.yyyy
 */
public class DateConverter {
    public static String DateToString(@NonNull Date date) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(date);
    }

    public static Date StringtoDate(@NonNull String date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(date);
    }
}
