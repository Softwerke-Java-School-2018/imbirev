package com.nikolay.imbirev.model.entities;

// here is the entity of 1 column of the table of database

import lombok.*;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Column {

    private String columnName;

    private String columnType;

    private boolean isNullableColumn;

    private boolean isAutoIncremented;

}
