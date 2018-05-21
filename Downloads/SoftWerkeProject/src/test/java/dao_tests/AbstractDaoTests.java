package dao_tests;

import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.dao.ClientDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.RequestCode;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import lombok.extern.log4j.Log4j;
import org.junit.*;

@Log4j
public class AbstractDaoTests {


    private AbstractDao dao;
    private static AbstractExecutor executor;

    @BeforeClass
    public static void setUpExecutor() {
        try {
            executor = AbstractExecutor.getAbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error("executor was not created in tests");
        }
    }

    @Before
    public void setUp() {
        dao = new ClientDao(executor);
    }


    @Test
    public void create_table_test_with_zero_columns_expected_syntax_error() {
        RequestCode code = dao.createTable("New_table", null);
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void create_table_test_with_null_name_expected_syntax_error() {
        RequestCode code = dao.createTable(null, null);
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void create_table_test_with_empty_name_expected_syntax_error() {
        RequestCode code = dao.createTable("", null);
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void create_table_test_with_empty_columns_expected_syntax_error() {
        RequestCode code = dao.createTable("New_table", new Column[]{});
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void create_table_test_with_column_array_expected_success() {
        RequestCode code = dao.createTable("table2", new Column[]{
                Column.builder().columnName("first_column").columnType("smallint").build(),
                Column.builder().columnName("sec_column").columnType("varchar (256)").build()
        });
        Assert.assertEquals(RequestCode.SUCCESS, code);
    }
    @Test
    public void create_another_table_with_the_same_name_expected_warning() {
        dao.createTable("table3", new Column[]{
                Column.builder().columnName("first_column").columnType("smallint").build(),
                Column.builder().columnName("sec_column").columnType("varchar (256)").build()
        });
        RequestCode code = dao.createTable("table3", new Column[]{
                Column.builder().columnName("first_column").columnType("smallint").build(),
                Column.builder().columnName("sec_column").columnType("varchar (256)").build()
        });
        Assert.assertEquals(RequestCode.WARNING, code);
    }
    @Test
    public void create_another_table_with_the_illegal_column_type() {
        RequestCode code = dao.createTable("table1", new Column[]{
                Column.builder().columnName("first_column").columnType("vuchar").build()
        });
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }
    @Test
    public void create_table_with_name_table_expected_sql_syntax_error() {
        RequestCode code = dao.createTable("table", new Column[]{
                Column.builder().columnName("first_column").columnType("smallint").build(),
                Column.builder().columnName("sec_column").columnType("varchar (256)").build()
        });
        Assert.assertEquals(RequestCode.SQL_SYNTAX_ERROR, code);
    }

    @AfterClass
    public static void clean() {
        AbstractDao dao;
        try {
            dao = new ClientDao(AbstractExecutor.getAbstractExecutor());
            RequestCode code0 = dao.dropTable("table2");
            RequestCode code = dao.dropTable("table3");
            if (code == RequestCode.SUCCESS && RequestCode.SUCCESS == code0) {
                log.info("cleaned");
            }
        } catch (DatabaseAccessException e) {
            log.error("not cleaned");
        }
    }
}