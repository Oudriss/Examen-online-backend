/*
 * JAVA Project
 */
package utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.util.StringConverter;

/**
 * Class to convert Date
 * @author EL OUIZ Badr
 */
public class DateConverter extends StringConverter<LocalDate> {

    private static String PATTERN = "dd/MM/yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDate fromDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public String toString(LocalDate dateObject) {
        if(dateObject != null) {
            return formatter.format(dateObject);
        } else {
            return "";
        }
    }

    @Override
    public LocalDate fromString(String string) {
        if(string != null && !string.isEmpty()) {
            return LocalDate.parse(string, formatter);
        } else {
            return null;
        }
    }

}
