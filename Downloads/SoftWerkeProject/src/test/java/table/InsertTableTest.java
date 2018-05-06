package table;

import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class InsertTableTest {

    private AbstractDao dao;
    private AbstractExecutor executor;
    private final static Logger log = Logger.getAnonymousLogger();

    @Before
    public void set() {
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            e.printStackTrace();
        }
        dao = new AbstractDao(executor);
        dao.createTable("Order_table", new Column[]{
                Column
                        .builder()
                        .columnName("pojo")
                        .columnType(" varchar (256) ")
                        .isAutoIncremented(false)
                        .isNullableColumn(false)
                        .build(),
                Column
                        .builder()
                        .columnName("date_column")
                        .columnType("date")
                        .isNullableColumn(false)
                        .isAutoIncremented(false)
                        .build()
    });
    }

    @Test
    public void test1() {
        int result = dao.insertIntoTable(null, null).getValue();
        Assert.assertEquals(20, result);
        log.info("test 1 passed");
    }
    @Test
    public void test2() {
        int result = dao.insertIntoTable(null, " ").getValue();
        Assert.assertEquals(20, result);
        log.info("test 2 passed");
    }
    @Test
    public void test3() {
        int result = dao.insertIntoTable(new Query[]{}, "table").getValue();
        Assert.assertEquals(20, result);
        log.info("test 3 passed");
    }
    @Test
    public void test4() {
        int result = dao.insertIntoTable(new Query[]{
                new Query("lol", "pol")
        }, null).getValue();
        Assert.assertEquals(20, result);
        log.info("test 4 passed");
    }
    @Test
    public void test5() {
        int result = dao.insertIntoTable(new Query[] {
                new Query(ClientTable.Cols.DATE_OF_BIRTH, "22/09/14")
        },"no table here").getValue();
        Assert.assertEquals(99, result);
        log.info("test 5 passed");
    }
    @Test
    public void test6() {
        int result = dao.insertIntoTable(new Query[] {
                new Query(ClientTable.Cols.DATE_OF_BIRTH, "22/09/14")
        },"Order_table").getValue();
        Assert.assertEquals(99, result);
        log.info("test 6 passed");
    }
    @Test
    public void test7() {
        int result = dao.insertIntoTable(new Query[] {
                new Query("pojo", "22/09/14")
        },"Order_table").getValue();
        Assert.assertEquals(99, result);
        log.info("test 7 passed");
    }
    @Test
    public void test8() {
        int result = dao.insertIntoTable(new Query[] {
                new Query("date_column", "22")
        },"Order_table").getValue();
        Assert.assertEquals(99, result);
        log.info("test 7 passed");
    }
}