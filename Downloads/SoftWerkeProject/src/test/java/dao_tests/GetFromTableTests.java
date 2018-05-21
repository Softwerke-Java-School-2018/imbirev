package dao_tests;

import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.dao.ClientDao;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.RequestCode;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import lombok.extern.log4j.Log4j;
import org.junit.*;

@Log4j
public class GetFromTableTests {

    private static AbstractExecutor abstractExecutor;
    private ClientDao dao;

    private static final String TABLE_NAME = "test_table";

    @BeforeClass
    public static void setUp() {
        try {
            abstractExecutor = AbstractExecutor.getAbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error("executor was not created");
        }
        AbstractDao dao = new ClientDao(abstractExecutor);
        RequestCode code = dao.createTable(TABLE_NAME, ClientTable.Cols.getCOLUMNS());
        if (code == RequestCode.SUCCESS) {
            log.info("test table created");
        }
    }

    @Before
    public void set() {
        dao = new ClientDao(abstractExecutor);
    }

    @Test
    public void get_with_null_table_name_syntax_error_expected() {
        RequestCode code = dao.getListFromTable(null, null, null);
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void get_with_null_fields_success_expected() {
        RequestCode code = dao.getListFromTable(TABLE_NAME, null, null);
        Assert.assertEquals(RequestCode.EMPTY_SET, code);
    }
    @Test
    public void get_with_empty_fields_success_expected() {
        RequestCode code = dao.getListFromTable(TABLE_NAME, new Query[]{}, new Column[]{});
        Assert.assertEquals(RequestCode.EMPTY_SET, code);
    }
    @Test
    public void get_with_illegal_sort_columns_sql_syntax_error_expected() {
        RequestCode code = dao.getListFromTable(TABLE_NAME, new Query[]{}, new Column[]{
                Column.builder().columnName("something").build()
        });
        Assert.assertEquals(RequestCode.SQL_SYNTAX_ERROR, code);
    }
    @Test
    public void get_with_illegal_query_columns_sql_syntax_error_expected() {
        RequestCode code = dao.getListFromTable(TABLE_NAME, new Query[]{
                new Query("first", "zero")
        }, new Column[]{
                Column.builder().columnName(ClientTable.Cols.FIRST_NAME).build()
        });
        Assert.assertEquals(RequestCode.SQL_SYNTAX_ERROR, code);
    }
    @Test
    public void get_with_legal_query_columns_and_sort_columns_success_expected() {
        RequestCode code = dao.getListFromTable(TABLE_NAME, new Query[]{
                new Query(ClientTable.Cols.DATE_OF_BIRTH, "12-01-1997")
        }, new Column[]{
                Column.builder().columnName(ClientTable.Cols.FIRST_NAME).build()
        });
        Assert.assertEquals(RequestCode.EMPTY_SET, code);
    }

    @AfterClass
    public static void clean() {
        DeleteTests.dropTable(TABLE_NAME);
    }
}