package person;

import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateClientTest {

    private final String name = "alex";
    private final String surname = "imbirev";
    private final LocalDate date = LocalDate.parse("30/06/1997", DateTimeFormatter.ofPattern("d/MM/yyyy"));


    private static ClientDbService service;

    @BeforeClass
    public static void setService() {
        service = new ClientDbService();
    }

    @Test
    public void getClient() {
        service.sendToTable(new Client.ClientBuilder()
                .setClientId()
                .setFirstName(name)
                .setLastName(surname)
                .setDateofBirth(date)
                .build());
        Client c = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                new Query(ClientTable.Cols.FIRST_NAME, name)
        });
        Assert.assertEquals(name, c.getFirstName());
    }

}
