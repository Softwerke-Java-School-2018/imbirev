package com.nikolay.imbirev.model.entities;

public class DeviceTable {

    public final static String TABLE_NAME = "device_table2";

    public static class Cols {

        public static final String ID = "device_id";
        public static final String MODEL = "device_model";
        public static final String PRODUCER = "device_producer";
        public static final String PRICE = "device_price";
        public static final String DATE_OF_STARTING_MANUFACTORING = "device_date";
        public static final String TYPE = "type";
        public static final String COLOR = "color";

        private static final String type = "varchar (256) ";

        public static Column[] columns = {
                Column.builder().columnName(ID).columnType("int").isNullableColumn(false).isAutoIncremented(true).build(),
                Column.builder().columnName(MODEL).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(PRODUCER).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(COLOR).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(TYPE).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(PRICE).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(DATE_OF_STARTING_MANUFACTORING).columnType("date").isNullableColumn(false).isAutoIncremented(false).build()
        };
    }
}