package com.nikolay.imbirev.model.entities;

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