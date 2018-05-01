package table;

import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateTableAbstractDaoTest {

    private AbstractExecutor executor;
    private AbstractDao dao;
    private final static Logger log = Logger.getLogger(CreateTableAbstractDaoTest.class);

    @Before
    public void setUp() {
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error(e.getMessage());
        }
        dao = new AbstractDao(executor);
    }

    @Test
    public void test1()  {
        int result = dao.createTable("NEW_TABLE", null).getValue();
        Assert.assertEquals(20, result);
        log.info("test 1 done");
    }
    @Test
    public void test2() {
        int result1 = dao.createTable("NEW_TABLE", new Column[]{}).getValue();
        Assert.assertEquals(20, result1);
        log.info("test 2 done");
    }
    @Test
    public void test3() {
        int result2 = dao.createTable(null, new Column[]{}).getValue();
        Assert.assertEquals(20, result2);
        log.info("test 3 done");
    }
    @Test
    public void test4() {
        int result3 = dao.createTable(" ", new Column[]{
                Column.builder().columnName("null ")
                        .columnType("varchar (256) ").isAutoIncremented(false)
                        .isNullableColumn(false).build()
        }).getValue();
        Assert.assertEquals(99 ,result3);
        log.info("test 4 done");
    }
    @Test
    public void test5()  {
        int result4 = dao.createTable("new Table", new Column[]{
                Column.builder().columnName("null ")
                        .columnType("varchar (256) ").isAutoIncremented(false)
                        .isNullableColumn(false).build()
        }).getValue();
        Assert.assertEquals(99,result4);
        log.info("test 5 done");
    }
    @Test
    public void test6() {
        int result5 = dao.createTable("new_table2", new Column[]{
                Column.builder().columnName("first").
                        columnType(" varchar(256)").isNullableColumn(false).isAutoIncremented(false).build()
        }).getValue();
        Assert.assertEquals(15,result5);
        log.info("test 6 done");
    }
    @Test
    public void test7()  {
        int result6 = dao.createTable("new_table2", new Column[]{
                Column.builder().columnName("first").
                        columnType(" varchar(256)").isNullableColumn(false).isAutoIncremented(false).build(),
                Column.builder().columnName("second").
                        columnType(" varchar(256)").isNullableColumn(false).isNullableColumn(false).build()
        }).getValue();
        Assert.assertEquals(15, result6);
        log.info("test 7 done");
    }




}
