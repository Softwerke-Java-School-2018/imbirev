package table;

import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.exceptions.ColumnCreateException;
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
    public void test1() throws ColumnCreateException {
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
    public void test4() throws ColumnCreateException {
        int result3 = dao.createTable(" ", new Column[]{
                new Column.ColumnBuilder().setName("null ")
                        .setType("varchar (256) ").setIsAuto(false)
                        .setIsNull(false).buildColumn()
        }).getValue();
        Assert.assertEquals(99 ,result3);
        log.info("test 4 done");
    }
    @Test
    public void test5() throws ColumnCreateException {
        int result4 = dao.createTable("new Table", new Column[]{
                new Column.ColumnBuilder().setName("null ")
                        .setType("varchar (256) ").setIsAuto(false)
                        .setIsNull(false).buildColumn()
        }).getValue();
        Assert.assertEquals(99,result4);
        log.info("test 5 done");
    }
    @Test
    public void test6() throws ColumnCreateException {
        int result5 = dao.createTable("new_table2", new Column[]{
                new Column.ColumnBuilder().setName("first").
                        setType(" varchar(256)").setIsNull(false).setIsAuto(false).buildColumn()
        }).getValue();
        Assert.assertEquals(15,result5);
        log.info("test 6 done");
    }
    @Test
    public void test7() throws ColumnCreateException {
        int result6 = dao.createTable("new_table2", new Column[]{
                new Column.ColumnBuilder().setName("first").
                        setType(" varchar(256)").setIsNull(false).setIsAuto(false).buildColumn(),
                new Column.ColumnBuilder().setName("second").
                        setType(" varchar(256)").setIsNull(false).setIsAuto(false).buildColumn()
        }).getValue();
        Assert.assertEquals(15, result6);
        log.info("test 7 done");
    }




}
