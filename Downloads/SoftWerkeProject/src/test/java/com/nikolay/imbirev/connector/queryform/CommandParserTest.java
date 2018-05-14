package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.model.entities.RequestCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandParserTest {

    private CommandParser parser;

    @Before
    public void setUp() {
        parser = CommandParser.getCommandParser();
    }

    @Test
    public void null_command_test() {
        String result = parser.parseCommand(null);
        Assert.assertEquals(RequestCode.ENTER_ERROR.toString(), result);
    }

    @Test
    public void empty_command_test() {
        String result = parser.parseCommand("");
        Assert.assertEquals(RequestCode.ENTER_ERROR.toString(), result);
    }

    @Test
    public void one_word_command_test() {
        String result = parser.parseCommand("client");
        Assert.assertEquals(RequestCode.ENTER_ERROR.toString(), result);
    }
    @Test
    public void illegal_operation_name_test() {
        String result = parser.parseCommand("crate client (first_name = nikolai, second_name = imbirev, date_of_birth = 30/06/1997)");
        Assert.assertEquals(RequestCode.ENTER_ERROR.toString(), result);
    }
    @Test
    public void illegal_entity_name_error() {
        String result = parser.parseCommand("create clent (first_name = nikolai, second_name = imbirev, date_of_birth = 30/06/1997)");
        Assert.assertEquals(RequestCode.ENTER_ERROR.toString(), result);
    }
    @Test
    public void illegal_amount_of_scopes() {
        String result = parser.parseCommand("create client first_name = nikolai, second_name = imbirev, date_of_birth = 30/06/1997)");
        Assert.assertEquals(RequestCode.ENTER_ERROR.toString(), result);
    }
    @Test
    public void no_scopes_test() {
        String result = parser.parseCommand("create client first_name = nikolai, second_name = imbirev, date_of_birth = 30/06/1997");
        Assert.assertEquals(RequestCode.ENTER_ERROR.toString(), result);
    }
    @Test
    public void illegal_scopes_test() {
        String result = parser.parseCommand("create client [first_name = nikolai, second_name = imbirev, date_of_birth = 30/06/1997}");
        Assert.assertEquals(RequestCode.ENTER_ERROR.toString(), result);
    }
    @Test
    public void illegal_scopes_test2() {
        String result = parser.parseCommand("create client {first_name = nikolai, second_name = imbirev, date_of_birth = 30/06/1997}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void good_test() {
        String result = parser.parseCommand("create client (first_name = nikolai, second_name = imbirev, date_of_birth = 30/06/1997)");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }

}