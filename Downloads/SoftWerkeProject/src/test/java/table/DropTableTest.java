package table;

import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class DropTableTest {

    private AbstractDao dao;
    private AbstractExecutor executor;
    private final static Logger log = Logger.getAnonymousLogger();

    @Before
    public void setUp() {
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            e.printStackTrace();
        }
        dao = new AbstractDao(executor);
    }

    @Test
    public void test1() {
        int result = dao.dropTable("").getValue();
        Assert.assertEquals(20, result);
        log.info("test 1 passed");
    }
    @Test
    public void test2() {
        int result = dao.dropTable(null).getValue();
        Assert.assertEquals(20, result);
        log.info("test 2 passed");
    }
    @Test
    public void test3() {
        int result = dao.dropTable("table").getValue();
        Assert.assertEquals(99, result);
        log.info("test 3 passed");
    }
    @Test
    public void test4() {
        int result = dao.dropTable("no").getValue();
        Assert.assertEquals(99, result);
        log.info("test 4 passed");
    }
    @Test
    public void test5() {
        int result = dao.dropTable("some_table").getValue();
        Assert.assertEquals(99, result);
        log.info("test 5 passed");
    }


}
