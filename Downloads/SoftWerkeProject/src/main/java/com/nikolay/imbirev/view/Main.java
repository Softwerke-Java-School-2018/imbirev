package com.nikolay.imbirev.view;

import com.nikolay.imbirev.connector.queryform.CommandParser;
import lombok.extern.java.Log;

import java.util.Scanner;

@Log
public class Main {

    /**
     * here is the start of the program
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
            try (Scanner scanner = new Scanner(System.in)) {
                CommandParser parser = new CommandParser();
                while (true) {
                    String command = scanner.nextLine();
                    log.info(command + " command");
                    System.out.println(parser.parseCommand(command));
                }
            }
    }
}