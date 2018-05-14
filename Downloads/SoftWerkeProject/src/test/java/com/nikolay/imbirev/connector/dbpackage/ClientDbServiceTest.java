package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.connector.queryform.CommandParser;
import com.nikolay.imbirev.model.entities.RequestCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientDbServiceTest {

    private CommandParser parser;

    @Before
    public void setUp() {
        parser = CommandParser.getCommandParser();
    }

    @Test
    public void update_test() {
        String result = parser.parseCommand("update client [first_name = nikolai, second_name = imbirev] (first_name = new_name, date_of_birth = 30/06/1997)");
        Assert.assertEquals(RequestCode.EMPTY_SET.toString(), result);
    }
    @Test
    public void update_test2_empty_set_expected() {
        String result = parser.parseCommand("update client [first_name = bibi] (first_name = new_name)");
        Assert.assertEquals(RequestCode.EMPTY_SET.toString(), result);
    }
    @Test
    public void update_test_empty_search_array() {
        String result = parser.parseCommand("update client [] (first_name = new_name)");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void update_test_empty_update_part() {
        String result = parser.parseCommand("update client [first_name = nikolay] ()");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
}