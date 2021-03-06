package com.nikolay.imbirev.model.entities;

import java.util.Arrays;
import java.util.List;

public class DeviceTable {

    private DeviceTable() {}

    public static final String TABLE_NAME = "device_table2";

    public static class Cols {

        private Cols() {}

        public static final String ID = "device_id";
        public static final String MODEL = "device_model";
        public static final String PRODUCER = "device_producer";
        public static final String PRICE = "device_price";
        public static final String DATE_OF_STARTING_MANUFACTORING = "device_date";
        public static final String TYPE = "type";
        public static final String COLOR = "color";

        private static final String COLUMN_TYPE = "varchar (256) ";

        private static Column[] getCOLUMNS() {
            return COLUMNS;
        }

        public static List<Column> getListOfColumns() {
            return Arrays.asList(getCOLUMNS());
        }

        private static final Column[] COLUMNS = {
                Column.builder().columnName(ID).columnType("int").isNullableColumn(false).isAutoIncremented(true).build(),
                Column.builder().columnName(MODEL).columnType(COLUMN_TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(PRODUCER).columnType(COLUMN_TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(COLOR).columnType(COLUMN_TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(TYPE).columnType(COLUMN_TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(PRICE).columnType(COLUMN_TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(DATE_OF_STARTING_MANUFACTORING).columnType("date").isNullableColumn(false).isAutoIncremented(false).build()
        };
    }
}