import com.nikolay.imbirev.connector.queryform.CommandParser;
import com.nikolay.imbirev.connector.queryform.QueryForm;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

@Log4j
public class CommandParserTest {

    private CommandParser commandParser = mock(CommandParser.class);

    @Before
    public void setup() {
        doThrow(new RuntimeException()).when(commandParser.getQueryForm());
    }


    @Test(expected = RuntimeException.class)
    public void parse_search_with_empty_params_null_expected() {
        String result = commandParser.parseCommand("get client [] {}");
    }
    @Test
    public void parse_search_with_one_sort_column_and_empty_query_null_expected() {
        String result = commandParser.parseCommand("get client [] {first_name}");
        Assert.assertNotNull(result);
    }
    @Test
    public void parse_search_with_two_sort_column_and_empty_query_null_expected() {
        String result = commandParser.parseCommand("get client [] {first_name, second_name}");
        Assert.assertNotNull(result);
    }
    @Test
    public void parse_search_with_two_sort_column_and_one_query_null_expected() {
        String result = commandParser.parseCommand("get client [first_name = Nikolay] {first_name, second_name}");
        Assert.assertNotNull(result);
    }
    @Test
    public void parse_search_with_illegal_amount_of_brackets_not_null_expected() {
        String string = commandParser.parseCommand("get client [] [] [] {}");
        Assert.assertNull(string);
    }
    @Test
    public void parse_search_with_illegal_amount_of_brackets2_not_null_expected() {
        String result = commandParser.parseCommand("get client [ {}");
        Assert.assertNull(result);
    }
    @Test
    public void parse_search_with_empty_params_with_immense_amount_of_tabs_null_expected() {
        String result = commandParser.parseCommand("        get         client          [        ]          {   }      ");
        Assert.assertNotNull(result);
    }

}
