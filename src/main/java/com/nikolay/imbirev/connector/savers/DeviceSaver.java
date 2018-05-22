package com.nikolay.imbirev.connector.savers;

import com.nikolay.imbirev.model.entities.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceSaver {

    private static DeviceSaver ourInstance = new DeviceSaver();
    private List<Device> deviceList;

    public static DeviceSaver getInstance() {
        return ourInstance;
    }

    private DeviceSaver() {
        deviceList = new ArrayList<>();
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
