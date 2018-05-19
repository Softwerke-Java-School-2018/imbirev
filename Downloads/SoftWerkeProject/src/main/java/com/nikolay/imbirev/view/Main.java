package com.nikolay.imbirev.view;

import com.nikolay.imbirev.connector.queryform.CommandParser;
import lombok.extern.log4j.Log4j;

import java.util.Scanner;

@Log4j
public class Main {
    public static void main(String[] args) {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    CommandParser parser = CommandParser.getCommandParser();
                    String command = scanner.nextLine();
                    if (command.trim().equals("exit")) break;
                    log.info(command + " command");
                    log.info(parser.parseCommand(command));
                }
            }
    }
}