package com.nikolay.imbirev.connector.queryform;

import java.time.LocalDate;

public interface DateParserInterface {

    LocalDate getLocalDateFromString(String input);
}
