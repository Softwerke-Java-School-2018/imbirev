package com.nikolay.imbirev.view;

import com.nikolay.imbirev.connector.checker.DeviceChecker;
import com.nikolay.imbirev.model.entities.Device;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DeviceDataParser {

    private String command;
    private DeviceChecker checker;
    private List<Device> devices;

    public DeviceDataParser(String command) {
        this.command = command;
        checker = new DeviceChecker();
        devices = new ArrayList<>();
    }

    public void parseCommand() {
        int t = command.indexOf(" ");
        String com = command.substring(0, t);
        String description = command.substring(14);
        switch (com.trim()) {
//            case "create": {
//                String[] objectParts = description.split(" ");
//                Device device = new Device.DeviceBuilder()
//                        .setDeviceId()
//                        .setModel(objectParts[0].trim())
//                        .setProducer(objectParts[1].trim())
//                        .setDate(getLocalDate(objectParts[3].trim()))
//                        .setColor(objectParts[5].trim())
//                        .setType(objectParts[4].trim())
//                        .setPrice(Double.parseDouble(objectParts[2].trim())).build();
//                checker.addToTable(device);
//                System.out.println("device created");
//                break;
//            }
//            case "delete": {
//                String[] objectParts = description.split(" ");
//                Device device = new Device.DeviceBuilder()
//                        .setDeviceId()
//                        .setModel(objectParts[0].trim())
//                        .setProducer(objectParts[1].trim())
//                        .setDate(getLocalDate(objectParts[3].trim()))
//                        .setColor(objectParts[5].trim())
//                        .setType(objectParts[4].trim())
//                        .setPrice(Double.parseDouble(objectParts[2].trim())).build();
//                checker.deleteFromTable(device);
//                System.out.println("device deleted");
//                break;
//            }
            case "get":
            {
                int f = command.indexOf('[');
                int h = command.indexOf(']');
                String d = command.substring(f+1, h);
                String[] desc = d.split(", ");
                String[] columns = new String[desc.length];
                String[] values = new String[desc.length];
                for (int i = 0; i < desc.length; i++) {
                    if (desc.length == 0) throw new IllegalArgumentException();
                    int y = desc[i].indexOf('=');
                    columns[i] = desc[i].substring(0, y).trim();
                    values[i] = desc[i].substring(y+1).trim();
                }
                Device dev = checker.getFromTable(columns, values);
                System.out.println(dev.getModel());
                System.out.println("device found");
                break;
            }
//            case "update":
//            {
//                int f = command.indexOf('[');
//                String des = command.substring(14, f);
//                String[] parts = des.split(" ");
//                Device device = new Device.DeviceBuilder()
//                        .setDeviceId()
//                        .setModel(parts[0]).build();
//                if (device == null) {
//                    System.out.println("Client won't found");
//                    throw new IllegalArgumentException();
//                }
//                int h = command.indexOf(']');
//                String d = command.substring(f+1, h);
//                String[] desc = d.split(", ");
//                String[] columns = new String[desc.length];
//                String[] values = new String[desc.length];
//                for (int i = 0; i < desc.length; i++) {
//                    if (desc.length == 0) throw new IllegalArgumentException();
//                    int y = desc[i].indexOf('=');
//                    columns[i] = desc[i].substring(0, y).trim();
//                    values[i] = desc[i].substring(y+1).trim();
//                }
//                checker.updateTable(device, columns, values);
//                System.out.println("client updated");
//                break;
//            }
////            case "list":
//            {
//                int f = command.indexOf('[');
//                int h = command.indexOf(']');
//                String d = command.substring(f+1, h);
//                String[] ss = d.split(", ");
//                int g = command.indexOf('{');
//                int s = command.indexOf('}');
//                String q = command.substring(g+1, s);
//                String[] desc = q.split(", ");
//                if (!d.equals("") && !q.equals("")) {
//                    String[] columns = new String[ss.length];
//                    String[] values = new String[ss.length];
//                    for (int i = 0; i < ss.length; i++) {
//                        int y = ss[i].indexOf('=');
//                        columns[i] = ss[i].substring(0, y).trim();
//                        values[i] = ss[i].substring(y+1).trim();
//                    }
//                    devices = checker.getListOfDevices(columns, values, desc);
//                    printDevice(devices);
//
//                } else if (d.equals("") && q.equals("")) {
//                    devices = checker.getListOfDevices(new String[]{}, new String[]{}, new String[]{});
//                    printDevice(devices);
//
//                }
//                else if (d.equals("") && !q.equals("")) {
//                    devices = checker.getListOfDevices(new String[]{}, new String[]{}, desc);
//                    printDevice(devices);
//
//                }
//                else {
//                    String[] columns = new String[ss.length];
//                    String[] values = new String[ss.length];
//                    for (int i = 0; i < ss.length; i++) {
//                        int y = ss[i].indexOf('=');
//                        columns[i] = ss[i].substring(0, y).trim();
//                        values[i] = ss[i].substring(y+1).trim();
//                    }
//                    devices = checker.getListOfDevices(columns, values, new String[]{});
//                    printDevice(devices);
//                }
//                break;
//            }
            default:
                throw new IllegalArgumentException();
        }
    }



    private LocalDate getLocalDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try {
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("date");
            throw new IllegalArgumentException();
        }
    }


    public void printDevice(List<Device> devices) {
        int i = 1;
        for (Device a : devices) {
            System.out.println(i++);
            System.out.println(a.getModel());
            System.out.println(a.getType());
            System.out.println(a.getProducer());
            System.out.println(a.getDateOfManufactoringStarted());
            System.out.println(a.getColor());
            System.out.println(a.getPrice());
            System.out.println(" ");
        }
    }
}
