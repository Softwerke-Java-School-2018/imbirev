package com.nikolay.imbirev.model.entities;

import com.nikolay.imbirev.model.exceptions.ColumnCreateException;

public class DeviceTable {

    public final static String TABLE_NAME = "device_table";


    public static class Cols {

        public static final String ID = "device_id";
        public static final String MODEL = "device_model";
        public static final String PRODUCER = "device_producer";
        public static final String PRICE = "device_price";
        public static final String DATE_OF_STARTING_MANUFACTORING = "device_date";
        public static final String TYPE = "type";
        public static final String COLOR = "color";

        private static final String type = "varchar (256) ";

        public static  Column[] columns;

        static {
            try {
                columns = new Column[]{
                                new Column.ColumnBuilder().setName(ID).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(MODEL).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(PRODUCER).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(COLOR).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(TYPE).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(PRICE).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(DATE_OF_STARTING_MANUFACTORING).setType(" date ").setIsNull(false).setIsAuto(false).buildColumn()

                        };
            } catch (ColumnCreateException e) {
                e.printStackTrace();
            }
        }


    }

}
