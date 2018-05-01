package com.nikolay.imbirev.view;

import com.nikolay.imbirev.connector.checker.SaleChecker;
import com.nikolay.imbirev.model.entities.Sale;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class SaleParsingData {

    private String command;
    private List<Sale> sales;
    private SaleChecker checker;


    SaleParsingData(String command) {
        this.command = command;
        checker = new SaleChecker();
        sales = new ArrayList<>();
    }

    public void parseCommand() {
        int t = command.indexOf(" ");
        String com = command.substring(0, t);
        String description = command.substring(14);
        switch (com.trim()) {
//            case "create":
//            {
//                String[] objectParts = description.split(" ");
//                Sale sale = new Sale.SaleBuilder()
//                        .setSaleId()
//                        .setPrice(Double.parseDouble(objectParts[2]))
//                        .setDateOfSale(getLocalDate(objectParts[1]))
//                        .setClient(objectParts[0])
//                        .build();
//                checker.addToTable(sale);
//                System.out.println("sale created");
//                break;
//            }
//            case "delete":
//            {
//                String[] objectParts = description.split(" ");
//                Sale sale = new Sale.SaleBuilder()
//                        .setSaleId()
//                        .setPrice(Double.parseDouble(objectParts[2]))
//                        .setDateOfSale(getLocalDate(objectParts[1]))
//                        .setClient(objectParts[0])
//                        .build();
//                checker.deleteFromTable(sale);
//                System.out.println("sale deleted");
//                break;
//            }
//            case "update":
//            {
//                int f = command.indexOf('[');
//                String des = command.substring(12, f);
//                String[] parts = des.split(" ");
//                Sale sale = new Sale.SaleBuilder()
//                        .setSaleId()
//                        .setClient(parts[0])
//                        .setPrice(Double.parseDouble(parts[1])).build();
//                if (sale == null) {
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
//                checker.updateSale(sale, columns, values);
//                System.out.println("client updated");
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
                Sale dev = checker.getFromTable(columns, values);
                if (dev == null) {
                    throw new IllegalArgumentException();
                }
                System.out.println(dev.getOverallPrice());
                System.out.println("sale found");
                break;
            }
//            case "list":
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
//                    sales = checker.getList(columns, values, desc);
//                    printSales(sales);
//
//                } else if (d.equals("") && q.equals("")) {
//                    sales = checker.getList(new String[]{}, new String[]{}, new String[]{});
//                    printSales(sales);
//
//                }
//                else if (d.equals("") && !q.equals("")) {
//                    sales = checker.getList(new String[]{}, new String[]{}, desc);
//                    printSales(sales);
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
//                    sales = checker.getList(columns, values, new String[]{});
//                    printSales(sales);
//                }
//                break;
//            }
            default:
                throw new IllegalArgumentException();
        }
    }


    private void printSales(List<Sale> saleList) {
        int i = 1;
        for (Sale a : saleList) {
            System.out.println(i++);
            System.out.println(a.getOverallPrice());
         //   System.out.println(a.getClientid());
            System.out.println(a.getDateOfSale());
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
}
