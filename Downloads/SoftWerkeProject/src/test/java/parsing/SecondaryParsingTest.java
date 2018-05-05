package parsing;

import com.nikolay.imbirev.model.entities.RequestCode;
import com.nikolay.imbirev.connector.queryform.CommandParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SecondaryParsingTest {

    private CommandParser parser;

    @Before
    public void setUp() {
        parser = new CommandParser();
    }

    @Test
    public void test() {
       String result =  parser.parseCommand("create     client      (first_name           =   nikolai    ,    second_name   =   imbirev, date_of_birth =   30/06/1997)");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void test1() {
        String result =  parser.parseCommand("create     client      (first_name           =   =   nikolai    ,    second_name ==  =   imbirev, date_of_birth =   30/06/1997)");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void test2() {
        String result = parser.parseCommand("get client [first_name = nikolai, second_name = imbirev] {}");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void test3() {
        String result = parser.parseCommand("get client [] {first_name}");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void test4() {
        String result = parser.parseCommand("get client [] {third_name}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void test5() {
        String result = parser.parseCommand("get client [third_name = alexeevich] {}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void test6() {
        String result = parser.parseCommand("get client [third_name = alexeevich, fourth_name = grisha] {}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void test7() {
        String result = parser.parseCommand("get client [first_name  alexeevich] {}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void test8() {
        String result = parser.parseCommand("get client [first_name  = alexeevich] {=}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void test9() {
        String result = parser.parseCommand("get client [] {}");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }


}