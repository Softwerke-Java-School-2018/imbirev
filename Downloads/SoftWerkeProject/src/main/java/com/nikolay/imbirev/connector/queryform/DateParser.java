package com.nikolay.imbirev.connector.queryform;

import java.sql.Date;
import java.time.LocalDate;

public class DateParser implements DateParserInterface {
    @Override
    public LocalDate sqlDateToLocalDate(Date date) {
        return date.toLocalDate();
    }

    @Override
    public Date localDateToSqlDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }
}
