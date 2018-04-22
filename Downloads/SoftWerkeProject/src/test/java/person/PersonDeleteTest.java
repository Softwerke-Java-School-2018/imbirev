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
    }

    @Test
    public void testMethod() {
        try {
            clientData = new ParsingClientData("delete niko imbirev 30/06/1997");
            clientData.parseCommand();
            Client client = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                    new Query(ClientTable.Cols.FIRST_NAME, "niko"),
                    new Query(ClientTable.Cols.SECOND_NAME, "imbirev")
            });
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        try {
            clientData = new ParsingClientData("delete imbirev 30/06/1997");
            clientData.parseCommand();
            Client client = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                    new Query(ClientTable.Cols.FIRST_NAME, "niko"),
                    new Query(ClientTable.Cols.SECOND_NAME, "imbirev")
            });
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        try {
            clientData = new ParsingClientData("delete client 30/06/1997");
            clientData.parseCommand();
            Client client = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                    new Query(ClientTable.Cols.FIRST_NAME, "niko"),
                    new Query(ClientTable.Cols.SECOND_NAME, "imbirev")
            });
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        try {
            clientData = new ParsingClientData("delete client niko imbirev 30/06/1997");
            clientData.parseCommand();
            Client client = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                    new Query(ClientTable.Cols.FIRST_NAME, "nikolay"),
                    new Query(ClientTable.Cols.SECOND_NAME, "imbirev")
            });
        } catch (IllegalArgumentException e) { // success deleting
            assertNotEquals("", e.getMessage());
        }

    }
}
