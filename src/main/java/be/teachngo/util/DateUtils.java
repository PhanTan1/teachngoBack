package be.teachngo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private final static SimpleDateFormat formatter5 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static Date stringToDate(String date) {
        try {
            return formatter5.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("date is not valid");
        }
    }

}
