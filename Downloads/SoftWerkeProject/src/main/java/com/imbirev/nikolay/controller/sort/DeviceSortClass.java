package com.imbirev.nikolay.controller.sort;

import com.imbirev.nikolay.model.beans.Device;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// this is sort class for devices using standart Collections.sort method
public class DeviceSortClass implements SortInterface<Device> {

    // initial list of devices
    private List<Device> devices;

    DeviceSortClass(List<Device> devices) {
        this.devices = devices;
    }

    /**
     * this class sorted list by alphabet
     * @param sortParameter is a definer of what parameter we should sort
     * @return
     */
    @Override
    public List<Device> sortByAlphabet(String sortParameter) {
        switch (sortParameter) {
            case "model":
                Collections.sort(devices, new Comparator<Device>() {
                    @Override
                    public int compare(Device o1, Device o2) {
                        return o1.getModel().compareTo(o2.getModel());
                    }
                });
                return devices;
            case "manufacturer":
                Collections.sort(devices, new Comparator<Device>() {
                    @Override
                    public int compare(Device o1, Device o2) {
                        return o1.getManufacturer().compareTo(o2.getManufacturer());
                    }
                });
                return devices;
            case "type":
                Collections.sort(devices, new Comparator<Device>() {
                    @Override
                    public int compare(Device o1, Device o2) {
                        return o1.getType().compareTo(o2.getType());
                    }
                });
                return devices;
            case "date":
                Collections.sort(devices, new Comparator<Device>() {
                    @Override
                    public int compare(Device o1, Device o2) {
                        return o1.getDateOfStartingManufactoring().compareTo(o2.getDateOfStartingManufactoring());
                    }
                });
                return devices;
            case "price":
                Collections.sort(devices, new Comparator<Device>() {
                    @Override
                    public int compare(Device o1, Device o2) {
                        return Double.compare(o2.getPrice(), o1.getPrice());
                    }
                });
                return devices;
                default: return devices;
        }
    }

    /**
     * here we sort by date
     * @return
     */
    @Override
    public List<Device> sortByDate() {
        Collections.sort(devices, new Comparator<Device>() {
            @Override
            public int compare(Device o1, Device o2) {
                return o1.getDateOfStartingManufactoring().compareTo(o2.getDateOfStartingManufactoring());
            }
        });
        return devices;
    }


}
