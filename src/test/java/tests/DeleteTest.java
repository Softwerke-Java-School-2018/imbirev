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
public class DeleteTest {

    private static AbstractExecutor abstractExecutor;
    private AbstractDao dao;
    private final static String TABLE_NAME = "delete_test_table";

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
    public void delete_with_null_table_name_syntax_error_expected() {
        RequestCode code = dao.deleteFromTable(null, new Query[]{});
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void delete_with_one_query_success_expected() {
        RequestCode code = dao.deleteFromTable(TABLE_NAME, new Query[]{
                new Query("first", "first_value")
        });
        Assert.assertEquals(RequestCode.SUCCESS, code);
    }
    @Test
    public void delete_with_illegal_query_sql_syntax_error_expected() {
        RequestCode code = dao.deleteFromTable(TABLE_NAME, new Query[]{
                new Query("firs", "first_value")
        });
        Assert.assertEquals(RequestCode.SQL_SYNTAX_ERROR, code);
    }
    @Test
    public void delete_with_empty_query_success_expected() {
        RequestCode code = dao.deleteFromTable(TABLE_NAME, new Query[]{});
        Assert.assertEquals(RequestCode.SUCCESS, code);
    }

    @AfterClass
    public static void clean() {
        dropTable(TABLE_NAME);
    }

    static void dropTable(String tableName) {
        AbstractDao dao;
        try {
            dao = new ClientDao(AbstractExecutor.getAbstractExecutor());
            RequestCode code = dao.dropTable(tableName);
            if (code == RequestCode.SUCCESS) {
                log.info("cleaned");
            }
        } catch (DatabaseAccessException e) {
            log.error("not cleaned");
        }
    }

}
