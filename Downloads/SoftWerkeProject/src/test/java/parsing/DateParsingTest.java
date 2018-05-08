package parsing;

import com.nikolay.imbirev.connector.queryform.CommandParser;
import com.nikolay.imbirev.model.entities.RequestCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DateParsingTest {

    private CommandParser parser;

    @Before
    public void setUp() {
        parser = new CommandParser();
    }


    @Test
    public void test() {
        String result = parser.parseCommand("get client [date_of_birth = 30-09-2001] {}");
        Assert.assertEquals(RequestCode.DATE_PARSING_ERROR.toString(), result);
    }
    @Test
    public void test1() {
        String result = parser.parseCommand("get client [date_of_birth = 2001-09-30] {}");
        Assert.assertEquals(RequestCode.DATE_PARSING_ERROR.toString(), result);
    }
    @Test
    public void test2() {
        String result = parser.parseCommand("get client [date_of_birth = 03-09-201] {}");
        Assert.assertEquals(RequestCode.DATE_PARSING_ERROR.toString(), result);
    }
    @Test
    public void test3() {
        String result = parser.parseCommand("get client [date_of_birth = 30:09:2001] {}");
        Assert.assertEquals(RequestCode.DATE_PARSING_ERROR.toString(), result);
    }
    @Test
    public void test4() {
        String result = parser.parseCommand("get client [date_of_birth = 3-9-2001] {}");
        Assert.assertEquals(RequestCode.DATE_PARSING_ERROR.toString(), result);
    }
    @Test
    public void test5() {
        String result = parser.parseCommand("get client [date_of_birth = 3/9/2001] {}");
        Assert.assertEquals(RequestCode.DATE_PARSING_ERROR.toString(), result);
    }
    @Test
    public void test6() {
        String result = parser.parseCommand("get client [date_of_birth = 03/09/2001] {}");
        Assert.assertEquals(RequestCode.EMPTY_SET.toString(), result);
    }
}