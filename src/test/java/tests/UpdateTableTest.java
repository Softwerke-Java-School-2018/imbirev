package tests;

import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.dao.ClientDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.RequestCode;
import com.nikolay.imbirev.connector.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import lombok.extern.log4j.Log4j;
import org.junit.*;

@Log4j
public class UpdateTableTest {

    private static AbstractExecutor abstractExecutor;
    private AbstractDao dao;
    private static final String TABLE_NAME = "update_test_table";

    @BeforeClass
    public static void setUp() {
        try {
            abstractExecutor = AbstractExecutor.getAbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error("executor was not created");
        }
        AbstractDao dao = new ClientDao(abstractExecutor);
        RequestCode code = dao.createTable(TABLE_NAME, new Column[]{
                Column.builder().columnName("first").columnType("smallint").isAutoIncremented(false).isNullableColumn(false).build(),
                Column.builder().columnName("second").columnType("varchar (256)").build()
        });
        if (code == RequestCode.SUCCESS) {
            log.info("test table created");
            dao.insertIntoTable(new Query[]{
                    new Query("first", "first_value"),
                    new Query("second", "second_value")
            }, TABLE_NAME);
        }
    }

    @Before
    public void setDao() {
        dao = new ClientDao(abstractExecutor);
    }

    @Test
    public void update_second_value_with_null_update_query_syntax_error_expected() {
        RequestCode code = dao.updateTable(TABLE_NAME,
                new Query[]{
                new Query("second", "second_value")
                }, null);
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void update_second_value_with_empty_update_query_syntax_error_expected() {
        RequestCode code = dao.updateTable(TABLE_NAME,
                new Query[]{
                        new Query("second", "second_value")
                }, new Query[]{});
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void update_second_value_with_no_queries_success_expected() {
        RequestCode code = dao.updateTable(TABLE_NAME,
                null , new Query[]{
                    new Query("second", "new_value")
                });
        Assert.assertEquals(RequestCode.SUCCESS, code);
    }
    @Test
    public void update_second_value_with_queries_success_expected() {
        RequestCode code = dao.updateTable(TABLE_NAME,
                new Query[]{
                    new Query("first", "first")
                },
                new Query[]{
                    new Query("second", "new_value2")
                });
        Assert.assertEquals(RequestCode.SUCCESS, code);
    }



    @AfterClass
    public static void clean() {
        DeleteTest.dropTable(TABLE_NAME);
    }

}
