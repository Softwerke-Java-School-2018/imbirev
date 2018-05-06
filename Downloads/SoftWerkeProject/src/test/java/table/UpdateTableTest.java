package table;

import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class UpdateTableTest {

    private AbstractExecutor executor;
    private AbstractDao dao;
    private final static Logger log = Logger.getAnonymousLogger();

    @Before
    public void setUp() {
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.info(e.getMessage());
        }
        dao = new AbstractDao(executor);
    }

    @Test
    public void test1() {
        int result = dao.updateTable(null, null, null).getValue();
        Assert.assertEquals(20, result);
        log.info("test 1 passed");
    }
    @Test
    public void test2() {
        int result = dao.updateTable("", new Query[]{}, new Query[]{}).getValue();
        Assert.assertEquals(20, result);
        log.info("test 2 passed");
    }
    @Test
    public void test3() {
        int result = dao.updateTable("no table here", null, null).getValue();
        Assert.assertEquals(20, result);
        log.info("test 3 passed");
    }
    @Test
    public void test4() {
        int result = dao.updateTable("no table here", null, new Query[]{
                new Query("lala", "pojo")
        }).getValue();
        Assert.assertEquals(99, result);
        log.info("test 4 passed");
    }
    @Test
    public void test5() {
        int result = dao.updateTable("Question_table", new Query[]{}, new Query[]{
                new Query("lala", "pojo")
        }).getValue();
        Assert.assertEquals(99, result);
        log.info("test 5 passed");
    }
    @Test
    public void test6() {
        int result = dao.updateTable("Question_table", null, new Query[]{
                new Query("text", "pojo")
        }).getValue();
        Assert.assertEquals(0, result);
        log.info("test 6 passed");
    }
    @Test
    public void test7() {
        int result = dao.updateTable("Question_table", null, new Query[]{}).getValue();
        Assert.assertEquals(20, result);
        log.info("test 7 passed");
    }
    @Test
    public void test8() {
        int result = dao.updateTable("Question_table", new Query[]{
                new Query("text", "pojo")
        }, new Query[]{
                new Query("text", "po")
        }).getValue();
        Assert.assertEquals(0, result);
        log.info("test 8 passed");
    }
}