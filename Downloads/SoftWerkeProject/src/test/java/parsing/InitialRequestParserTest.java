package parsing;

import com.nikolay.imbirev.connector.queryform.CommandParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InitialRequestParserTest {


    private CommandParser parser;


    @Before
    public void setUp() {
        parser = new CommandParser();
    }

    @Test
    public void test() {
        String result = parser.parseCommand("");
        Assert.assertEquals("ENTER_ERROR", result);
    }
    @Test
    public void test1() {
        String result = parser.parseCommand(null);
        Assert.assertEquals("ENTER_ERROR", result);
    }
    @Test
    public void test2() {
        String result = parser.parseCommand("crate client");
        Assert.assertEquals("ENTER_ERROR", result);
    }
    @Test
    public void test3() {
        String result = parser.parseCommand("update device");
        Assert.assertEquals("ENTER_ERROR", result);
    }
    @Test
    public void test4() {
        String result = parser.parseCommand("update     device     [     ]    ");
        Assert.assertNull(result);
    }
    @Test
    public void test5() {
        String result = parser.parseCommand("create client  {} [] [] []");
        Assert.assertEquals("ENTER_ERROR", result);
    }
    @Test
    public void test6() {
        String result = parser.parseCommand("create client  { ]");
        Assert.assertEquals("ENTER_ERROR", result);
    }
    @Test
    public void test7() {
        String result = parser.parseCommand("              create       client  {         ");
        Assert.assertEquals("ENTER_ERROR", result);
    }
    @Test
    public void test8() {
        String result = parser.parseCommand("              create       client  {)         ");
        Assert.assertEquals("ENTER_ERROR", result);
    }


}
