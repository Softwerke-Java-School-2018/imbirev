package person;

import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.view.ParsingClientData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SearchClientTest {

    private ClientDbService service;


    @Before
    public void initialize() {
        service = new ClientDbService();
    }

    @Test
    public void searchTest() {
        Client c = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                new Query(ClientTable.Cols.SECOND_NAME, "vanya")
        });
        Assert.assertNull(c);
        Client s = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                new Query(ClientTable.Cols.SECOND_NAME, "imbirev")
        });
        Assert.assertNotNull(s);
        Client a = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                new Query(ClientTable.Cols.SECOND_NAME, "imbirev"),
                new Query(ClientTable.Cols.FIRST_NAME, "alex")
        });
        Assert.assertNotNull(a);
        Client q = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                new Query(ClientTable.Cols.SECOND_NAME, "imbirev"),
                new Query(ClientTable.Cols.FIRST_NAME, "alex"),
                new Query(ClientTable.Cols.DATE_OF_BIRTH, "30/06/1997")
        });
        Assert.assertNotNull(q);
        try {
            Client g = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{});
        } catch (IllegalArgumentException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

}
