package person;

import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.view.ParsingClientData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class PersonDeleteTest {


    private ClientDbService service;
    private ParsingClientData clientData;

    @Before
    public void startTesting() {
        service = new ClientDbService();
        clientData = new ParsingClientData("delete client nikolay imbirev 30/06/1997");
        clientData.parseCommand();
    }


    @Test
    public void testMethod() {
        try {
            Client client = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                    new Query(ClientTable.Cols.FIRST_NAME, "nikolay"),
                    new Query(ClientTable.Cols.SECOND_NAME, "imbirev")
            });
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
    }
}
