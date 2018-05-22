package com.nikolay.imbirev.model.entities;

public class ClientTable {

    private ClientTable() {}

    public static final String TABLE_NAME = "Client_table2";

    public static class Cols {

        private Cols() {}

        private static final String TYPE = "varchar (256)";

        public static final String ID = "clientId";
        public static final String FIRST_NAME = "first_name";
        public static final String SECOND_NAME = "second_name";
        public static final String DATE_OF_BIRTH = "date_of_birth";

        public static Column[] getCOLUMNS() {
            return COLUMNS;
        }

        private static final Column[] COLUMNS = {
                Column.builder().columnName(ID).columnType("int").isNullableColumn(false).isAutoIncremented(true).build(),
                Column.builder().columnName(FIRST_NAME).columnType(TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(SECOND_NAME).columnType(TYPE).isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName(DATE_OF_BIRTH).columnType("date").isNullableColumn(false).isAutoIncremented(false).build()
        };
    }
}