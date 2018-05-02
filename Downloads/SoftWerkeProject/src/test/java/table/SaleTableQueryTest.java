package table;

import com.nikolay.imbirev.connector.savers.SaleSaver;
import com.nikolay.imbirev.model.dao.SaleDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.SaleTable;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SaleTableQueryTest {

    private SaleDao dao;

    private AbstractExecutor executor;

    private final static Logger log = Logger.getLogger(SaleTableQueryTest.class);


    @Before
    public void setUp() {
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error(e.getMessage());
        }
        dao = new SaleDao(executor);
    }

    @Test
    public void test() {
        int result = dao.getListFromTable("sale_table", new Query[]{
                new Query(SaleTable.Cols.PRICE, "25.0")
        }, new Column[]{
                Column.builder().columnName(SaleTable.Cols.DATE_OF_SALE).build()
        }).getValue();
        Assert.assertEquals(0, result);
    }
    @Test
    public void test1() {
        int result = dao.getListFromTable("sale_table",null, new Column[]{
                Column.builder().columnName(SaleTable.Cols.DATE_OF_SALE).build()
        }).getValue();
        Assert.assertEquals(0, result);
    }
    @Test
    public void test2() {
        int result = dao.getListFromTable("sale_table",null, null).getValue();
        Assert.assertEquals(0, result);
    }

}