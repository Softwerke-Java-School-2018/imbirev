package com.nikolay.imbirev.connector.queryform;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser implements DateParserInterface {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDate sqlDateToLocalDate(Date date) {
        return date.toLocalDate();
    }

    @Override
    public Date localDateToSqlDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    @Override
    public LocalDate getLocalDateFromString(String input) {
        return LocalDate.parse(input, formatter);
    }
}
