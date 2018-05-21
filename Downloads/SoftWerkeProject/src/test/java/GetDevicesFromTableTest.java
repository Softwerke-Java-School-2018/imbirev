import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.dao.DeviceDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.DeviceTable;
import com.nikolay.imbirev.model.entities.RequestCode;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import lombok.extern.log4j.Log4j;
import org.junit.*;

@Log4j
public class GetDevicesFromTableTest {

    private static AbstractExecutor abstractExecutor;
    private DeviceDao dao;

    private static final String TABLE_NAME = "test_table";

    @BeforeClass
    public static void setUp() {
        try {
            abstractExecutor = AbstractExecutor.getAbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error("executor was not created");
        }
        AbstractDao dao = new DeviceDao(abstractExecutor);
        RequestCode code = dao.createTable(TABLE_NAME, DeviceTable.Cols.getCOLUMNS());
        if (code == RequestCode.SUCCESS) {
            log.info("test table created");
        }
    }

    @Before
    public void set() {
        dao = new DeviceDao(abstractExecutor);
    }

    @Test
    public void get_devices_from_empty_table_syntax_error_expected() {
        RequestCode code = dao.getListFromTable("", null, null);
        Assert.assertEquals(RequestCode.SYNTAX_ERROR, code);
    }

    @Test
    public void get_devices_with_null_query_and_one_sort_column_empty_set_expected() {
        RequestCode code = dao.getListFromTable(TABLE_NAME, null, new Column[]{
                Column.builder().columnName(DeviceTable.Cols.TYPE).build()
        });
        Assert.assertEquals(RequestCode.EMPTY_SET, code);
    }
    @Test
    public void get_devices_with_null_query_and_several_sort_columns_empty_set_expected() {
        RequestCode code = dao.getListFromTable(TABLE_NAME, null, new Column[]{
                Column.builder().columnName(DeviceTable.Cols.TYPE).build(),
                Column.builder().columnName(DeviceTable.Cols.PRODUCER).build(),
                Column.builder().columnName(DeviceTable.Cols.MODEL).build()
        });
        Assert.assertEquals(RequestCode.EMPTY_SET, code);
    }

    @AfterClass
    public static void clean() {
        DeleteTest.dropTable(TABLE_NAME);
    }
}