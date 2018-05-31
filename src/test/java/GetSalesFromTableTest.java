import com.nikolay.imbirev.model.dao.AbstractDao;
import com.nikolay.imbirev.model.dao.SaleDao;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.model.entities.Query;
import com.nikolay.imbirev.model.entities.RequestCode;
import com.nikolay.imbirev.model.entities.SaleTable;
import com.nikolay.imbirev.model.exceptions.DatabaseAccessException;
import com.nikolay.imbirev.model.executors.AbstractExecutor;
import lombok.extern.log4j.Log4j;
import org.junit.*;

@Log4j
public class GetSalesFromTableTest {

    private static AbstractExecutor abstractExecutor;
    private SaleDao dao;

    private static final String TABLE_NAME = "test_table";

    @BeforeClass
    public static void setUp() {
        try {
            abstractExecutor = AbstractExecutor.getAbstractExecutor();
        } catch (DatabaseAccessException e) {
            log.error("executor was not created");
        }
        AbstractDao dao = new SaleDao(abstractExecutor);
        RequestCode code = dao.createTable(TABLE_NAME,(Column[]) SaleTable.Cols.getListOfColumns().toArray());
        if (code == RequestCode.SUCCESS) {
            log.info("test table created");
        }
        dao.insertIntoTable(new Query[]{
                        new Query(SaleTable.Cols.CLIENT_NAME, "nikolay"),
                        new Query(SaleTable.Cols.CLIENT_SURNAME, "imbirev"),
                        new Query(SaleTable.Cols.PRICE, "25000"),
                        new Query(SaleTable.Cols.DATE_OF_SALE, "2017-02-01")
                }
                ,TABLE_NAME);
        dao.insertIntoTable(new Query[]{
                        new Query(SaleTable.Cols.CLIENT_NAME, "nikolay"),
                        new Query(SaleTable.Cols.CLIENT_SURNAME, "imbirev"),
                        new Query(SaleTable.Cols.PRICE, "37000"),
                        new Query(SaleTable.Cols.DATE_OF_SALE, "2015-02-01")
                }
                ,TABLE_NAME);
    }

    @Before
    public void set() {
        dao = new SaleDao(abstractExecutor);
    }


    @Test
    public void get_sale_from_table_with_one_query_and_one_sort_column_expected_success() {
        RequestCode code = dao.getListFromTable(TABLE_NAME, new Query[]{
                new Query(SaleTable.Cols.PRICE, "25000")
        }, new Column[]{
                Column.builder().columnName(SaleTable.Cols.CLIENT_NAME).build()
        });
        Assert.assertEquals(RequestCode.SUCCESS, code);
    }
    @Test
    public void get_sale_from_table_with_two_query_and_one_sort_column_expected_success() {
        RequestCode code = dao.getListFromTable(TABLE_NAME, new Query[]{
                new Query(SaleTable.Cols.PRICE, "25000"),
                new Query(SaleTable.Cols.CLIENT_SURNAME, "imbirev")
        }, new Column[]{
                Column.builder().columnName(SaleTable.Cols.CLIENT_NAME).build()
        });
        Assert.assertEquals(RequestCode.SUCCESS, code);
    }
    @Test
    public void get_sale_from_table_with_two_unknown_query_and_one_sort_column_expected_success() {
        RequestCode code = dao.getListFromTable(TABLE_NAME, new Query[]{
                new Query("цена", "25000"),
                new Query("фамилия", "imbirev")
        }, new Column[]{
                Column.builder().columnName(SaleTable.Cols.CLIENT_NAME).build()
        });
        Assert.assertEquals(RequestCode.SQL_SYNTAX_ERROR, code);
    }

    @AfterClass
    public static void clean() {
        DeleteTest.dropTable(TABLE_NAME);
    }
}
