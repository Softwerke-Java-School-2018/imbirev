package com.nikolay.imbirev.connector.queryform;

import java.sql.Date;
import java.time.LocalDate;

public interface DateParserInterface {

    LocalDate getLocalDateFromString(String input);
}
