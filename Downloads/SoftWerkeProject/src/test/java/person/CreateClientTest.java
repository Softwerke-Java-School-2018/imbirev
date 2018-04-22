package person;

import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.view.ParsingClientData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class CreateClientTest {

    private final String name = "alex";
    private final String surname = "imbirev";
    private final LocalDate date = LocalDate.parse("30/06/1997", DateTimeFormatter.ofPattern("d/MM/yyyy"));


    private static ParsingClientData clientData;

    private static ClientDbService service;

    @BeforeClass
    public static void setService() {
        service = new ClientDbService();
    }

    @Test
    public void getClient() {
        try {
            clientData = new ParsingClientData("create table nikolay imbirev 30/06/1997");
            clientData.parseCommand();
            Client c = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                    new Query(ClientTable.Cols.FIRST_NAME, name)
            });
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }

        try {
            clientData = new ParsingClientData("create client 3030 imbirev 30/06/1997");
            clientData.parseCommand();
            Client a = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                    new Query(ClientTable.Cols.FIRST_NAME, name)
            });
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        try {
            clientData = new ParsingClientData("create nikolay imbirev 30/06/1997");
            clientData.parseCommand();
            Client a = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                    new Query(ClientTable.Cols.FIRST_NAME, name)
            });
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        try {
            clientData = new ParsingClientData("client nikolay imbirev 30/06/1997");
            clientData.parseCommand();
            Client a = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                    new Query(ClientTable.Cols.FIRST_NAME, name)
            });
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        clientData = new ParsingClientData("create client niko imbirev 30/06/1997");
        clientData.parseCommand();
        Client a = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                new Query(ClientTable.Cols.FIRST_NAME, name)
        });
        assertNotNull(a);
        try {
            clientData = new ParsingClientData(" client niko imbirev 30/06/1997");
            clientData.parseCommand();
            Client s = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                    new Query(ClientTable.Cols.FIRST_NAME, name)
            });
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
    }

}