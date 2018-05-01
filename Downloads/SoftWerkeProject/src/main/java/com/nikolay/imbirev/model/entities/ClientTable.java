package com.nikolay.imbirev.model.entities;

public class ClientTable {

    public static final String TABLE_NAME = "Client_table";

    public static class Cols {
        private static final String type = "varchar (256)";

        public final static String ID = "clientId";
        public final static String FIRST_NAME = "first_name";
        public final static String SECOND_NAME = "second_name";
        public final static String DATE_OF_BIRTH = "date_of_birth";

        public static Column[] columns = {
                Column.builder().columnName(ID).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(FIRST_NAME).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(SECOND_NAME).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(DATE_OF_BIRTH).columnType("date").isNullableColumn(false).isAutoIncremented(false).build()
        };
    }
}