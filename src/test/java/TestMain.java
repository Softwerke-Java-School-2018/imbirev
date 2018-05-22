import com.nikolay.imbirev.connector.queryform.CommandParser;
import com.nikolay.imbirev.model.entities.RequestCode;
import com.nikolay.imbirev.view.Main;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

// cool
public class TestMain {

    @Test
    public void parse_null_string_expected_enter_error() {
        CommandParser parser1 = mock(CommandParser.class);
        when(parser1.parseCommand(anyString())).thenReturn(RequestCode.ENTER_ERROR.toString());
        String result = parser1.parseCommand(new Main().start(""));
        Assert.assertEquals(RequestCode.ENTER_ERROR.toString(), result);
    }

    @Test
    public void parse_exit_command_empty_output() {
        Assert.assertEquals("Bye", new Main().start("exit"));
    }
    @Test
    public void parse_exit_ignore_equals_command_empty_output() {
        Assert.assertEquals("Bye", new Main().start("eXiT"));
    }

    @Test
    public void test() {
        CommandParser parser1 = mock(CommandParser.class);
        when(parser1.parseCommand(anyString())).thenReturn(RequestCode.EMPTY_SET.toString());
        Assert.assertEquals(RequestCode.EMPTY_SET.toString(), parser1.parseCommand(new Main().start("get client [] {}")));
    }
}