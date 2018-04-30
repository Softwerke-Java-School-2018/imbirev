package com.nikolay.imbirev.model.entities;


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
                columns = new Column[]{
                                Column.builder().columnName(ID).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                                Column.builder().columnName(ClIENT_ID).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                                Column.builder().columnName(PRICE).columnType(type).isNullableColumn(false).isAutoIncremented(false).build(),
                                Column.builder().columnName(DATE_OF_SALE).columnType("date").isNullableColumn(false).isAutoIncremented(false).build()
                        };
        }


    }


}
