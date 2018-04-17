package com.nikolay.imbirev.model.entities;

// here is the entity of 1 column of the table of database
public class Column {

    private final String columnName;

    private final String columnType;

    private final boolean isNullableColumn;

    private final boolean isAutoIncremented;

    public Column(String name, String type, boolean isNull, boolean isAuto) {
        this.columnName = name;
        this.columnType = type;
        this.isAutoIncremented = isAuto;
        this.isNullableColumn = isNull;
    }

    // this is builder (see userKrohne class for details)
    public static class ColumnBuilder {

        private  String columnName;

        private  String columnType;

        private  boolean isNullableColumn;

        private  boolean isAutoIncremented;


        public ColumnBuilder setName(String name) {
            this.columnName = name;
            return this;
        }
        public ColumnBuilder setType(String type) {
            this.columnType = type;
            return this;
        }
        public ColumnBuilder setIsNull(boolean isNull) {
            this.isNullableColumn = isNull;
            return this;
        }
        public ColumnBuilder setIsAuto(boolean isAuto) {
            this.isAutoIncremented = isAuto;
            return this;
        }

        public Column buildColumn() {
            return new Column(columnName, columnType, isNullableColumn, isAutoIncremented);
        }


    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public boolean isNullableColumn() {
        return isNullableColumn;
    }

    public boolean isAutoIncremented() {
        return isAutoIncremented;
    }
}
