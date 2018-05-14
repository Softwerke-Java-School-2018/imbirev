package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.connector.queryform.CommandParser;
import com.nikolay.imbirev.model.entities.RequestCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SaleDbServiceTest {

    private CommandParser commandParser;

    @Before
    public void setUp() {
        commandParser = CommandParser.getCommandParser();
    }
    @Test
    public void create_sale_test() {
        String result = commandParser.parseCommand("create sale (client_name = Nikolay, client_surname = Imbirev, device_price = 100200, device_date = 01/05/2017)");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void unfully_create_sale_test() {
        String result = commandParser.parseCommand("create sale (client_surname = Imbirev, device_price = 100200, device_date = 01/05/2017)");
        Assert.assertEquals(RequestCode.DATABASE_ERROR.toString(), result);
    }
    @Test
    public void update_sale_test() {
        String result = commandParser.parseCommand("update sale [client_name = Nikolay] (client_surname = Pan)");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void empty_update_sale_test() {
        String result = commandParser.parseCommand("update sale [] (client_surname = Pan)");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void empty_update_sale_test2() {
        String result = commandParser.parseCommand("update sale [] ()");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void delete_sale_test() {
        String result = commandParser.parseCommand("delete sale (client_name = Nik)");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
}