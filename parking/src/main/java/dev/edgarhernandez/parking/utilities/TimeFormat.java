package dev.edgarhernandez.parking.utilities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeFormat {
    public static String convertToString(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return date.format(formatter);
    }

    public static LocalDateTime convertToDateTime(String date){
        LocalDateTime localDateTime = null;
        String format = "yyyy-MM-dd'T'HH:mm:ss";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            localDateTime = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing the date: " + e.getMessage());
            // You can choose to handle this exception differently based on your requirements
        }
        return localDateTime;
    }

    public static LocalDateTime parseDurationToLocalDateTime(LocalDateTime referenceDateTime, Duration duration) {
        return referenceDateTime.plus(duration);
    }
}
