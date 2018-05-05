package com.nikolay.imbirev.connector.queryform;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser implements DateParserInterface {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDate getLocalDateFromString(String input) {
        return LocalDate.parse(input, formatter);
    }
}
