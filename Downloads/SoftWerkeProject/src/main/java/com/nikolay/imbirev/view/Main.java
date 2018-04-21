package com.nikolay.imbirev.view;

import com.nikolay.imbirev.connector.checker.ClientChecker;
import com.nikolay.imbirev.connector.checker.Command;

import java.util.Scanner;

public class Main {

    private static ClientChecker clientChecker;
    private static ParsingClientData dataParser;
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
            dataParser = new ParsingClientData(command);
            dataParser.parseCommand();
        } catch (IllegalArgumentException e) {
            System.out.println("Please check your input");
            getCommand();
        }

    }
}
