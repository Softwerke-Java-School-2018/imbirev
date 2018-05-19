package com.nikolay.imbirev.view;

import com.nikolay.imbirev.connector.queryform.CommandParser;
import com.nikolay.imbirev.model.entities.RequestCode;
import lombok.extern.log4j.Log4j;

import java.util.Scanner;

@Log4j
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();
                String result = main.start(command);
                if (result.equalsIgnoreCase("Bye")) {
                    log.info("Bye");
                    break;
                }
                else {
                    log.info(main.start(scanner.nextLine()));
                }
            }
        }
    }

    public String start(String command) {
                if (command == null) return RequestCode.ENTER_ERROR.toString();
                if (command.trim().equalsIgnoreCase("exit")) return "Bye";
                CommandParser parser = CommandParser.getCommandParser();
                log.info(command + " command");
                return parser.parseCommand(command);
    }
}