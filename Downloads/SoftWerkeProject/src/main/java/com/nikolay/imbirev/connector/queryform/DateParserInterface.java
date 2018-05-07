package com.nikolay.imbirev.connector.queryform;

import java.time.LocalDate;

@FunctionalInterface
public interface DateParserInterface {

    LocalDate getLocalDateFromString(String input);
}