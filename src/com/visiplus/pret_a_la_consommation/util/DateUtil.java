package com.visiplus.pret_a_la_consommation.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse("01/" + dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Format de date incorrect. Veuillez saisir la date au format MM/yyyy.");
            return LocalDate.MIN;
        }
    }
}
