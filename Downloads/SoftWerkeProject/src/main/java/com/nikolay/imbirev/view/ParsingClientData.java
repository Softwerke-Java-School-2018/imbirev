package com.nikolay.imbirev.view;

import com.nikolay.imbirev.connector.checker.ClientChecker;
import com.nikolay.imbirev.model.entities.Client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParsingClientData {

    private String command;
    private Client client;
    private ClientChecker checker;

    /**
     * constructor method to create command and clientChecker to work with connector part
     * @param command
     * @throws IllegalArgumentException if we have illegal symbols in the input
     */
    public ParsingClientData(String command) throws IllegalArgumentException {
        checkForIllegalChars(command);
        this.command = command;
        checker = new ClientChecker();
    }

    /**
     * here we check all matches of input and if all right - go to transforming client object from the string
     */
    public void parseCommand() {
        String typeOfCommand = command.substring(0, 13); // this a length of a string with word client and command
        String[] typeParts = typeOfCommand.split(" ");
        if (!typeParts[1].trim().equals("client")) {
            throw new IllegalArgumentException();
        } else {
            if (typeParts[0].trim().equals("update") || typeParts[0].trim().equals("get")
                    || typeParts[0].trim().equals("delete") || typeParts[0].trim().equals("create")) {
                String description = command.substring(14);
                String[] objectParts = description.split(" ");
                if (objectParts.length != 3 && typeParts[0].trim().equals("create")) // we need all fields to create object
                    throw new IllegalArgumentException();
                if (objectParts.length == 0)
                    throw new IllegalArgumentException();
                if (objectParts.length != 3 && typeParts[0].trim().equals("delete")) // we need all fields to delete object
                    throw new IllegalArgumentException();
                switch (typeParts[0].trim()) {
                    case "create": {
                        Client client = getClientFromData(objectParts[0].trim(), objectParts[1].trim(), objectParts[2].trim());
                        checker.addToTable(client);
                        System.out.println("client created");
                        break;
                    }
                    case "delete": {
                        Client client = getClientFromData(objectParts[0].trim(), objectParts[1].trim(), objectParts[2].trim());
                        checker.deleteFromTable(client);
                        System.out.println("client deleted");
                        break;
                    }
                    case "update": {
                        int f = command.indexOf('[');
                        Client client = getClientFromData(objectParts[0].trim(), objectParts[1].trim());
                        if (client == null) {
                            System.out.println("Client won't found");
                            throw new IllegalArgumentException();
                        }
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
                        checker.updateClient(client, columns, values);
                        System.out.println("client updated");
                        break;
                    }

                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * this method transform array of strings to an client object
     * @param name
     * @param secondName
     * @param dateOfBirth
     * if these strings are not matches - throw new IllegalArgumentException
     */
    public Client getClientFromData(String name, String secondName, String dateOfBirth) {
        if (!name.matches("^[a-zA]+$")) {
            throw new IllegalArgumentException();
        }
        if (!secondName.matches("^[a-zA]+$")) {
            throw new IllegalArgumentException();
        }
        else {
            client = new Client.ClientBuilder()
                    .setClientId()
                    .setDateofBirth(getLocalDate(dateOfBirth))
                    .setFirstName(name)
                    .setLastName(secondName)
                    .build();
            return client;
        }
    }
    // we dont need date of birth to update client
    public Client getClientFromData(String name, String secondName) {
        if (!name.matches("^[a-zA]+$")) {
            System.out.println(name);
            throw new IllegalArgumentException();
        }
        else if (!secondName.matches("^[a-zA]+$")) {
            System.out.println(secondName);
            throw new IllegalArgumentException();
        }
        else {
            client = new Client.ClientBuilder()
                    .setClientId()
                    .setFirstName(name)
                    .setLastName(secondName)
                    .build();
            return client;
        }
    }

    /**
     * transform text input into LocalDate format
     * @param input
     * @return LocalDate format or throw new IllegalArgumentException
     */
    private LocalDate getLocalDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try {
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * cheker for illegal dots, commas and etc chars in input command
     * @param input
     * throw new IllegalArgumentException if we have these symbols
     */
    private void checkForIllegalChars(String input) {
        for (int i = 0; i < input.length(); i++) {
            char a = input.charAt(i);
            if (a == '.' || a == '-')
                throw new IllegalArgumentException();
        }
    }
}
