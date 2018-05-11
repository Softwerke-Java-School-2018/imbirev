package com.nikolay.imbirev.model.entities;

public class SaleTable {

    public static final String TABLE_NAME = "sale_table2";

    public static class Cols {

        public static final String ID = "sale_id";
        public static final String CLIENT_NAME = "client_name";
        public static final String CLIENT_SURNAME = "client_surname";
        public static final String PRICE = "device_price";
        public static final String DATE_OF_SALE = "device_date";

        private static final String TYPE = "varchar (256) ";

        public static  Column[] COLUMNS = {
                Column.builder().columnName(ID).columnType("int").isNullableColumn(false).isAutoIncremented(true).build(),
                Column.builder().columnName(CLIENT_NAME).columnType(TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(CLIENT_SURNAME).columnType(TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(PRICE).columnType(TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(DATE_OF_SALE).columnType("date").isNullableColumn(false).isAutoIncremented(false).build()
        };
    }
}