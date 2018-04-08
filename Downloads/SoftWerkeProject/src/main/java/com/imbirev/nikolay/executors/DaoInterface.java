package com.imbirev.nikolay.executors;


// abstract class for daoclasses with basic methods

abstract interface DaoInterface {

    public abstract void createTable();

    public abstract void deleteFromTable(int id);

    public  abstract void dropTable();
}
