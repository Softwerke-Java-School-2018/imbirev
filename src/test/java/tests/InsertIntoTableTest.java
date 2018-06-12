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
public class InsertIntoTableTest {

    private static AbstractExecutor abstractExecutor;
    private AbstractDao dao;

    @BeforeClass
    public static void setUp() {
        try {
            abstractExecutor = AbstractExecutor.getAbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error("executor was not created");
        }
        AbstractDao dao = new ClientDao(abstractExecutor);
        RequestCode code = dao.createTable("test_table", new Column[]{
                Column.builder().columnName("first").columnType("smallint").isAutoIncremented(false).isNullableColumn(false).build(),
                Column.builder().columnName("second").columnType("varchar (256)").build()
        });
        if (code == RequestCode.SUCCESS) {
            log.info("test table created");
        }
    }

    @Before
    public void set() {
        dao = new ClientDao(abstractExecutor);
    }

    @Test
    public void test_for_unknown_table_syntax_error_expected() {
        RequestCode code = dao.insertIntoTable(new Query[]{
                new Query("oo", "oo")
        }, "some_table_i_dont_know_what");
        Assert.assertEquals(RequestCode.SQL_SYNTAX_ERROR, code);
    }
    @Test
    public void test_for_null_query_syntax_error_expected() {
        RequestCode code = dao.insertIntoTable(null
        , "some_table_i_dont_know_what");
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void test_for_null_table_name_syntax_error_expected() {
        RequestCode code = dao.insertIntoTable(new Query[]{}
                , null);
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void test_insert_into_table_with_notnull_column_sql_syntax_error_expected() {
        RequestCode code = dao.insertIntoTable(new Query[]{
                new Query("second", "value")
        }, "test_table");
        Assert.assertEquals(RequestCode.DATABASE_ERROR, code);
    }
    @Test
    public void test_insert_into_table_with_illegal_column_value_sql_syntax_error_expected() {
        RequestCode code = dao.insertIntoTable(new Query[]{
                new Query("first", "value"),
                new Query("second", "something")
        }, "test_table");
        Assert.assertEquals(RequestCode.DATABASE_ERROR, code);
    }


    @AfterClass
    public static void clean() {
        DeleteTest.dropTable("test_table");
    }
}
