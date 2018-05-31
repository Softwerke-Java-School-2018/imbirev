package com.nikolay.imbirev.model.entities;

import java.util.Arrays;
import java.util.List;

public class SaleTable {

    private SaleTable() {}

    public static final String TABLE_NAME = "sale_table2";

    public static class Cols {

        private Cols() {}

        private static Column[] getCOLUMNS() {
            return COLUMNS;
        }

        public static List<Column> getListOfColumns() {
            return Arrays.asList(getCOLUMNS());
        }

        public static final String ID = "sale_id";
        public static final String CLIENT_NAME = "client_name";
        public static final String CLIENT_SURNAME = "client_surname";
        public static final String PRICE = "device_price";
        public static final String DATE_OF_SALE = "device_date";

        private static final String TYPE = "varchar (256) ";

        private static final Column[] COLUMNS = {
                Column.builder().columnName(ID).columnType("int").isNullableColumn(false).isAutoIncremented(true).build(),
                Column.builder().columnName(CLIENT_NAME).columnType(TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(CLIENT_SURNAME).columnType(TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(PRICE).columnType(TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(DATE_OF_SALE).columnType("date").isNullableColumn(false).isAutoIncremented(false).build()
        };
    }
}