package table;

import com.nikolay.imbirev.connector.savers.SaverClients;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.dao.ClientDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientQueryTest {

    private ClientDao dao;
    private AbstractExecutor executor;
    private final static Logger log = Logger.getLogger(ClientQueryTest.class);

    @Before
    public void setUp() {
        try {
            executor = new AbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error(e.getMessage());
        }
        dao = new ClientDao(executor);
    }

    @Test
    public void test1() {
        int result = dao.getListFromTable(null, null, null).getValue();
        Assert.assertEquals(20, result);
    }
    @Test
    public void test2() {
        int result = dao.getListFromTable("", new Query[]{}, new Column[]{}).getValue();
        Assert.assertEquals(20, result);
    }
    @Test
    public void test3() {
        int result = dao.getListFromTable("Client_table", new Query[]{
                new Query("lolo", "pojo")
        }, new Column[]{}).getValue();
        Assert.assertEquals(99, result);
    }
    @Test
    public void test4() {
        int result = dao.getListFromTable("table", new Query[]{
                new Query("lolo", "pojo")
        }, new Column[]{}).getValue();
        Assert.assertEquals(99, result);
    }
    @Test
    public void test5() {
        int result = dao.getListFromTable("Client_table", new Query[]{
                new Query("first_name", "")
        }, new Column[]{}).getValue();
        Assert.assertEquals(2, result);
    }
    @Test
    public void test6() {
        int result = dao.getListFromTable("Client_table", new Query[]{
                new Query("text", "po")
        }, new Column[]{
                Column.builder().columnName("pojo").columnType("poho").build()
        }).getValue();
        Assert.assertEquals(99, result);
    }
    @Test
    public void test7() {
        int result = dao.getListFromTable("Client_table", new Query[]{
                new Query("first_name", "egor")
        }, new Column[]{
                Column.builder().columnName("first_name").build()
        }).getValue();
        Assert.assertEquals(0, result);
    }
    @Test
    public void test8() {
        int result = dao.getListFromTable("Client_table", new Query[]{
                new Query("second_name", "imbirev")
        }, new Column[]{
                Column.builder().columnName("first_name").build()
        }).getValue();
        System.out.println(SaverClients.getInstance().getClients().size());
        Assert.assertEquals(0, result);
    }

}