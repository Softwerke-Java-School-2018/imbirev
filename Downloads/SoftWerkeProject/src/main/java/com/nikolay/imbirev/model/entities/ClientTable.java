package com.nikolay.imbirev.model.entities;

import com.nikolay.imbirev.model.exceptions.ColumnCreateException;

public class ClientTable {

    public static final String TABLE_NAME = "Client_table";

    public static class Cols {
        private static final String type = "varchar (256)";

        public final static String ID = "clientId";
        public final static String FIRST_NAME = "first_name";
        public final static String SECOND_NAME = "second_name";
        public final static String DATE_OF_BIRTH = "date_of_birth";

        public static Column[] columns;

        static {
            try {
                columns = new Column[]{
                                new Column.ColumnBuilder().setName(ID).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(FIRST_NAME).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(SECOND_NAME).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(DATE_OF_BIRTH).setType("date").setIsNull(false).setIsAuto(false).buildColumn()
                        };
            } catch (ColumnCreateException e) {
                e.printStackTrace();
            }
        }
    }
}