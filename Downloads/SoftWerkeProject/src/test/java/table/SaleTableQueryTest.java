package table;

import com.nikolay.imbirev.model.dao.SaleDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.SaleTable;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class SaleTableQueryTest {

    private SaleDao dao;

    private AbstractExecutor executor;

    private final static Logger log = Logger.getAnonymousLogger();


    @Before
    public void setUp() {
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.info(e.getMessage());
        }
        dao = new SaleDao(executor);
        dao.dropTable("sale_table");
        dao.createTable(SaleTable.TABLE_NAME, SaleTable.Cols.columns);
    }

    @Test
    public void test() {
        int result = dao.getListFromTable("sale_table", new Query[]{
                new Query(SaleTable.Cols.PRICE, "25.0")
        }, new Column[]{
                Column.builder().columnName(SaleTable.Cols.DATE_OF_SALE).build()
        }).getValue();
        Assert.assertEquals(2, result);
    }
    @Test
    public void test1() {
        int result = dao.getListFromTable("sale_table",null, new Column[]{
                Column.builder().columnName(SaleTable.Cols.DATE_OF_SALE).build()
        }).getValue();
        Assert.assertEquals(2, result);
    }
    @Test
    public void test2() {
        int result = dao.getListFromTable("sale_table",null, null).getValue();
        Assert.assertEquals(2, result);
    }


}