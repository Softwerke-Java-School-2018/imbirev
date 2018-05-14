package com.nikolay.imbirev.connector.queryform;

import com.nikolay.imbirev.model.entities.RequestCode;
import org.junit.Assert;
import org.junit.Test;

public class QueryFormTest {

    private CommandParser commandParser;

    @Test
    public void illegal_conditions_for_operation() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("delete client {first_name = nikola, second_name = imbirev, date_of_birth = 30/06/1997}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void illegal_conditions_for_operation2() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("create client [first_name = nikola, second_name = imbirev, date_of_birth = 30/06/1997]");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void illegal_conditions_for_operation3() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("get client (first_name = nikola, second_name = imbirev, date_of_birth = 30/06/1997)");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void illegal_conditions_for_operation4() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("update client [first_name = nikola, second_name = imbirev, date_of_birth = 30/06/1997]");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void empty_sort_columns() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("get client [first_name = n, second_name = i, date_of_birth = 30/06/1997] {}");
        Assert.assertEquals(RequestCode.EMPTY_SET.toString(), result);
    }
    @Test
    public void empty_search_columns() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("get client [] {first_name, second_name}");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void empty_all_columns_for_search() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("get client [] {}");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void illegal_empty_all_columns_for_update() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("update client [] ()");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void illegal_date_format() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("get client [first_name = n, second_name = i, date_of_birth = 30-06-1998] {}");
        Assert.assertEquals(RequestCode.DATE_PARSING_ERROR.toString(), result);
    }
    @Test
    public void illegal_date_format2() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("get client [first_name = n, second_name = i, date_of_birth = 30/06/199] {}");
        Assert.assertEquals(RequestCode.DATE_PARSING_ERROR.toString(), result);
    }
    @Test
    public void illegal_date_format3() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("get client [first_name = n, second_name = i, date_of_birth = 1997/06/30] {first_name, second_name}");
        Assert.assertEquals(RequestCode.DATE_PARSING_ERROR.toString(), result);
    }
    @Test
    public void illegal_amount_of_equals() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("get client [first_name = n =, second_name = i =, date_of_birth == 1997/06/30] {first_name, second_name}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void illegal_column_name() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("get client [firstname = n, secondname = i, dateof_birth = 1997/06/30] {first_name, second_name}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void illegal_column_name2() {
        commandParser = CommandParser.getCommandParser();
        String result = commandParser.parseCommand("get client [first_name = n, second_name = i, date_of_birth = 1997/06/30] {frst_name, scond_name}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
}