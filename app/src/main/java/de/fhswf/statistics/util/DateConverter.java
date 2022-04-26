package de.fhswf.statistics.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateConverter {
    public static String DateToString(Date date){
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(date);
    }
    public static Date StringtoDate(String date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(date);
    }
}
