package person;

import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.view.ParsingClientData;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateClientTest {

    private static final String name = "nikolay";
    private static final String surname = "imbirev";


    private static final String newName = "egor";


    private static ParsingClientData clientData;
    private static ClientDbService service;

    @BeforeClass
    public static void setService() {
        service = new ClientDbService();
        clientData = new ParsingClientData("update client " + name + " " + surname + " [name = " + newName + "]");
    }

    @Test
    public  void getClient() {
        clientData.parseCommand();
        Client c = service.getFromTable(ClientTable.TABLE_NAME, new Query[]{
                new Query(ClientTable.Cols.FIRST_NAME, "egor")
        });

        Assert.assertNotNull(c);
    }


}
