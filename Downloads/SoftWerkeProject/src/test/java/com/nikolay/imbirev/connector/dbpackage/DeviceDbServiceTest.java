package com.nikolay.imbirev.connector.dbpackage;

import com.nikolay.imbirev.connector.queryform.CommandParser;
import com.nikolay.imbirev.model.entities.RequestCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeviceDbServiceTest {

    private CommandParser commandParser;

    @Before
    public void update() {
        commandParser = CommandParser.getCommandParser();
    }

    @Test
    public void create_device_test() {
        String result = commandParser.parseCommand("create device (device_model = Galaxy8, device_producer = Samsung," +
                " device_price = 59000, type = phone, color = black, device_date = 20/02/2016)");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void create_unfully_device_test() {
        String result = commandParser.parseCommand("create device (device_producer = Samsung," +
                " device_price = 59000, type = phone, color = black, device_date = 20/02/2016)");
        Assert.assertEquals(RequestCode.DATABASE_ERROR.toString(), result);
    }
    @Test
    public void update_illegal_scopes_device_test() {
        String result = commandParser.parseCommand("create device {device_model = Galaxy8, device_producer = Samsung," +
                " device_price = 59000, type = phone, color = black, device_date = 20/02/2016}");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void update_null_scopes_device_test() {
        String result = commandParser.parseCommand("create device [device_model = Galaxy8] ()");
        Assert.assertEquals(RequestCode.DATA_ERROR.toString(), result);
    }
    @Test
    public void update_device_test() {
        String result = commandParser.parseCommand("update device [device_model = Galaxy8] (device_model = Iphone, device_producer = Apple)");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void get_device_test() {
        String result = commandParser.parseCommand("get device [] {}");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void get_device_test2() {
        String result = commandParser.parseCommand("get device [device_model = Gala] {}");
        Assert.assertEquals(RequestCode.EMPTY_SET.toString(), result);
    }
    @Test
    public void get_device_test3() {
        String result = commandParser.parseCommand("get device [device_model = Iphone] {device_model}");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }
    @Test
    public void delete_device_test3() {
        String result = commandParser.parseCommand("delete device (device_date = 10/02/2015)");
        Assert.assertEquals(RequestCode.SUCCESS.toString(), result);
    }

}