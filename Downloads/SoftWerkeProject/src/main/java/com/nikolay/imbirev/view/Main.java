package com.nikolay.imbirev.view;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CommandParserInterface parserInterface = new CommandParser();
            String command = scanner.nextLine();
            System.out.println(parserInterface.parseCommand(command));
        }
    }
}
