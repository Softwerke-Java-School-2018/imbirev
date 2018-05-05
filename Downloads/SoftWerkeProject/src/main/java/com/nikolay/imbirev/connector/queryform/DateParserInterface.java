package com.nikolay.imbirev.connector.queryform;

import java.sql.Date;
import java.time.LocalDate;

public interface DateParserInterface {

    LocalDate sqlDateToLocalDate(Date date);

    Date localDateToSqlDate(LocalDate localDate);

    LocalDate getLocalDateFromString(String input);

}
