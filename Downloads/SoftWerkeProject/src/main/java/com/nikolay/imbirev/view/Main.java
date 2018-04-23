package com.nikolay.imbirev.view;

import com.nikolay.imbirev.connector.checker.ClientChecker;
import com.nikolay.imbirev.connector.checker.Command;

import java.util.Scanner;

public class Main {

    private static ClientChecker clientChecker;
    private static Scanner scanner;


    public static void main(String[] args) throws InterruptedException {
        scanner = new Scanner(System.in);
        printHelpCommands();
        getCommand();
    }

    private static void getCommand() throws InterruptedException {
        while (true) {
            String command = scanner.nextLine();
            resolveCommand(command);
        }
    }


    private  static void printHelpCommands() {
        clientChecker = new ClientChecker();
        for (Command a : clientChecker.help()) {
            System.out.println(a.getName());
            System.out.println(a.getInstructions());
        }
    }

    private static void resolveCommand(String command) throws InterruptedException {
        try {
            String desc = command.substring(0, 14);
            String[] params = desc.split(" ");
            switch (params[1]) {
                case "client":
                    ParsingClientData dataParser = new ParsingClientData(command);
                    dataParser.parseCommand();
                    break;
                case "sale":
                    SaleParsingData datapars = new SaleParsingData(command);
                    datapars.parseCommand();
                    break;
                case "device":
                    DeviceDataParser parser = new DeviceDataParser(command);
                    parser.parseCommand();
                    break;
                    default:
                        throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Please check your input");
            getCommand();
        }
    }
}
