package person;

import com.nikolay.imbirev.connector.checker.Query;
import com.nikolay.imbirev.connector.dbpackage.ClientDbService;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.ClientTable;
import com.nikolay.imbirev.model.entities.Column;
import com.nikolay.imbirev.view.ParsingClientData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class SortClientTest {

    private List<Client> clientList;

    private static ClientDbService dbService;

    @Before
    public void initConstructors() {
        clientList = new ArrayList<>();
        dbService = new ClientDbService();
    }

   // @Test
//    public void sortTest() {
//        clientList = dbService.getList(new Query[]{}, new Column[]{});
//        Assert.assertEquals("alex", clientList.get(4).getFirstName().trim());
//
//        clientList = dbService.getList(new Query[]{
//                new Query(ClientTable.Cols.DATE_OF_BIRTH, "30/06/1997")
//        }, new Column[]{new Column(ClientTable.Cols.FIRST_NAME, null, false, false)});
//        Assert.assertEquals("sergey", clientList.get(2).getFirstName().trim());
//
//        clientList = dbService.getList(new Query[]{
//                new Query(ClientTable.Cols.DATE_OF_BIRTH, "30/06/1997")
//        }, new Column[]{new Column(ClientTable.Cols.SECOND_NAME, null, true, true)});
//        Assert.assertEquals("egor", clientList.get(0).getFirstName().trim());
//
//        try {
//            clientList = dbService.getList(new Query[]{
//                    new Query(ClientTable.Cols.FIRST_NAME, "30/06/1997")
//            }, new Column[]{new Column(ClientTable.Cols.FIRST_NAME, null, false, false)});
//        } catch (IllegalArgumentException e) {
//            assertNotEquals("", e.getMessage());
//        }
//        try {
//            clientList = dbService.getList(new Query[]{
//                    new Query(ClientTable.Cols.SECOND_NAME, "30/06/1997")
//            }, new Column[]{new Column(ClientTable.Cols.FIRST_NAME, null, false, false)});
//        } catch (IllegalArgumentException e) {
//            assertNotEquals("", e.getMessage());
//        }
//    }

}
