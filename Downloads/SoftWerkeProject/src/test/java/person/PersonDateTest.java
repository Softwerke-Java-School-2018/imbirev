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

import static org.junit.Assert.*;

public class PersonDateTest {

    private static ParsingClientData clientData;


    @BeforeClass
    public static void testParserCommands() {
        clientData = new ParsingClientData("");
    }


    @Test
    public void dateTest() {
        try {
            Client c = clientData.getClientFromData("nikolay", "imbirev", "06/10/");
            LocalDate date = c.getDateOfBirth();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        try {
            Client c = clientData.getClientFromData("nikolay", "imbirev", "06-10-1997");
            LocalDate date = c.getDateOfBirth();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        try {
            Client c = clientData.getClientFromData("nikolay", "imbirev", "01/01/199");
            LocalDate date = c.getDateOfBirth();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        try {
            Client c = clientData.getClientFromData("nikolay", "imbirev", "1997/10/06");
            LocalDate date = c.getDateOfBirth();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        try {
            Client c = clientData.getClientFromData("nikolay", "imbirev", "06//1997");
            LocalDate date = c.getDateOfBirth();
        } catch (IllegalArgumentException e) {
            assertNotEquals("", e.getMessage());
        }
        Client c = clientData.getClientFromData("nikolay", "imbirev", "06/10/1997");
        LocalDate date = c.getDateOfBirth();
        assertNotNull(date);
    }

}
