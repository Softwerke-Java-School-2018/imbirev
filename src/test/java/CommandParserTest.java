import com.nikolay.imbirev.connector.queryform.CommandParser;
import com.nikolay.imbirev.connector.queryform.QueryForm;
import lombok.extern.log4j.Log4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@Log4j
@RunWith(MockitoJUnitRunner.class)
public class CommandParserTest {

    @Mock
    private QueryForm queryForm;

    @InjectMocks
    private CommandParser commandParser;

    @Before
    public void setup() {
        when(queryForm.createQuery(any(), any(), any(), any(), any())).thenReturn("Ok");
    }

    @Test
    public void parse_search_with_empty_params_null_expected() {
        String result = commandParser.parseCommand("get client [] {}");
        Assert.assertEquals("Ok", result);
    }
    @Test
    public void parse_search_with_one_sort_column_and_empty_query_null_expected() {
        String result = commandParser.parseCommand("get client [] {first_name}");
        Assert.assertEquals("Ok", result);
    }
    @Test
    public void parse_search_with_two_sort_column_and_empty_query_null_expected() {
        String result = commandParser.parseCommand("get client [] {first_name, second_name}");
        Assert.assertEquals("Ok", result);
    }
    @Test
    public void parse_search_with_two_sort_column_and_one_query_null_expected() {
        String result = commandParser.parseCommand("get client [first_name = Nikolay] {first_name, second_name}");
        Assert.assertEquals("Ok", result);
    }
    @Test
    public void parse_search_with_illegal_amount_of_brackets_not_null_expected() {
        String string = commandParser.parseCommand("get client [] [] [] {}");
        Assert.assertNotEquals("Ok", string);
    }
    @Test
    public void parse_search_with_illegal_amount_of_brackets2_not_null_expected() {
        String result = commandParser.parseCommand("get client [ {}");
        Assert.assertNotEquals("Ok", result);
    }
    @Test
    public void parse_search_with_empty_params_with_immense_amount_of_tabs_null_expected() {
        String result = commandParser.parseCommand("        get         client          [        ]          {   }      ");
        Assert.assertEquals("Ok", result);
    }
}