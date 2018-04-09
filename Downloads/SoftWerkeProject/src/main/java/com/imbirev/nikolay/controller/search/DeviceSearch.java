package com.imbirev.nikolay.controller.search;

import com.imbirev.nikolay.model.beans.Device;

import java.time.LocalDate;
import java.util.List;

import static com.imbirev.nikolay.controller.search.ClientSearch.rank;

/**
 * this class is a search class for devices
 */
public class DeviceSearch implements SearchInterface<Device> {

    private List<Device> devices;

    public DeviceSearch(List<Device> devices) {
        this.devices = devices;
    }

    /**
     *
     * @param param is the parameter of search
     * @param keyOfSearch is the searching object
     * @return device from devicelist or null
     */
    @Override
    public Device searchByParam(String param, String keyOfSearch) {
        switch (param) {
            case "model":
            {String[] array = new String[devices.size()];
                for (int i = 0; i < array.length; i++) array[i] = devices.get(i).getModel();
                int position = rank(keyOfSearch, array, 0, array.length);
                if (position >= 0) return devices.get(position);
                else return null;}
            case "type":
            {String[] array = new String[devices.size()];
                for (int i = 0; i < array.length; i++) array[i] = devices.get(i).getType();
                int position = rank(keyOfSearch, array, 0, array.length);
                if (position >= 0) return devices.get(position);
                else return null;}
            case "Date of manufacturing":
            {LocalDate[] array = new LocalDate[devices.size()];
                for (int i = 0; i < array.length; i++) array[i] = devices.get(i).getDateOfStartingManufactoring();
                int position = rank(keyOfSearch, array, 0, array.length);
                if (position >= 0) return devices.get(position);
                else return null;}
            case "Device id":
            {int[] array = new int[devices.size()];
                for (int i = 0; i < array.length; i++) array[i] = devices.get(i).getDeviceId();
                int position = rank(Integer.valueOf(keyOfSearch), array, 0, array.length);
                if (position >= 0) return devices.get(position);
                else return null;}
            default:return null;
        }
    }
}
