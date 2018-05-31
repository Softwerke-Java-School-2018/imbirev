package com.nikolay.imbirev.view;

import com.mysql.cj.core.util.StringUtils;
import com.nikolay.imbirev.connector.queryform.CommandParser;
import com.nikolay.imbirev.model.entities.RequestCode;
import lombok.extern.log4j.Log4j;

import java.util.Scanner;

@Log4j
public class Main {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> log.error("Something went wrong, please fire your developer"));
        Main main = new Main();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();
                if (command.equalsIgnoreCase("Bye")) {
                    log.info("Bye");
                    break;
                }
                else {
                    String result = main.start(command);
                    log.info(result);
                }
            }
        }
    }

    public String start(String command) {
        if (StringUtils.isNullOrEmpty(command)) return RequestCode.ENTER_ERROR.toString();
        CommandParser parser = CommandParser.getCommandParser();
        log.info(command + " command");
        return parser.parseCommand(command);
    }
}