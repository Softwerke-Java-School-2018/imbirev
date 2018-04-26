package com.nikolay.imbirev.model.entities;

import com.nikolay.imbirev.model.exceptions.ColumnCreateException;

public class SaleTable {

    public final static String TABLE_NAME = "sale_table";

    public static class Cols {

        public static final String ID = "sale_id";
        public static final String ClIENT_ID = "client_id_for_sale";
        public static final String PRICE = "device_price";
        public static final String DATE_OF_SALE = "device_date";

        private static final String type = "varchar (256) ";

        public static  Column[] columns;

        static {
            try {
                columns = new Column[]{
                                new Column.ColumnBuilder().setName(ID).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(ClIENT_ID).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(PRICE).setType(type).setIsNull(false).setIsAuto(false).buildColumn(),
                                new Column.ColumnBuilder().setName(DATE_OF_SALE).setType(" date ").setIsNull(false).setIsAuto(false).buildColumn()
                        };
            } catch (ColumnCreateException e) {


            }
        }


    }


}
