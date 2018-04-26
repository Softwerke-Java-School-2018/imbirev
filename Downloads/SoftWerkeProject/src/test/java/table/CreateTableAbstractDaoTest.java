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
    public void testMethod() throws ColumnCreateException {
        int result = dao.createTable("NEW_TABLE", null);
        Assert.assertEquals(result, 20);
        log.info("test 1 done");
        int result1 = dao.createTable("NEW_TABLE", new Column[]{});
        Assert.assertEquals(result1, 20);
        log.info("test 2 done");
        int result2 = dao.createTable(null, new Column[]{});
        Assert.assertEquals(result2, 20);
        log.info("test 3 done");
        int result3 = dao.createTable(" ", new Column[]{
                    new Column.ColumnBuilder().setName("null ")
                            .setType("varchar (256) ").setIsAuto(false)
                            .setIsNull(false).buildColumn()
            });
        Assert.assertEquals(result3, 99);
        log.info("test 4 done");
        int result4 = dao.createTable("new Table", new Column[]{
                new Column.ColumnBuilder().setName("null ")
                        .setType("varchar (256) ").setIsAuto(false)
                        .setIsNull(false).buildColumn()
        });
        Assert.assertEquals(result4, 99);
        log.info("test 5 done");
        int result5 = dao.createTable("new_table2", new Column[]{
                new Column.ColumnBuilder().setName("first").
                        setType(" varchar(256)").setIsNull(false).setIsAuto(false).buildColumn()
        });
        Assert.assertEquals(result5, 0);
        log.info("test 6 done");
        int result6 = dao.createTable("new_table2", new Column[]{
                new Column.ColumnBuilder().setName("first").
                        setType(" varchar(256)").setIsNull(false).setIsAuto(false).buildColumn(),
                new Column.ColumnBuilder().setName("second").
                        setType(" varchar(256)").setIsNull(false).setIsAuto(false).buildColumn()
        });
        Assert.assertEquals(result6, 15);
        log.info("test 7 done");
    }


}
