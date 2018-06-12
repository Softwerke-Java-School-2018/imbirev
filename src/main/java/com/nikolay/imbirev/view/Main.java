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
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String command = scanner.nextLine();
                if ("Bye".equalsIgnoreCase(command)) {
                    break;
                }
                else {
                    log.info(new Main().start(command));
                }
            }
            log.info("Bye");
        }
    }

    public String start(String command) {
        if (StringUtils.isNullOrEmpty(command)) {
            return RequestCode.ENTER_ERROR.toString();
        }
        log.info(command + " command");
        return CommandParser.getCommandParser().parseCommand(command);
    }
}