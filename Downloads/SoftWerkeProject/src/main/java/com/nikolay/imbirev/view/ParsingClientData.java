package com.nikolay.imbirev.view;

import com.nikolay.imbirev.connector.checker.ClientChecker;
import com.nikolay.imbirev.model.entities.Client;
import com.nikolay.imbirev.model.entities.Column;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ParsingClientData {

    private String command;
    private Client client;
    private ClientChecker checker;
    private List<Client> clients;

    /**
     * constructor method to create command and clientChecker to work with connector part
     * @param command
     * @throws IllegalArgumentException if we have illegal symbols in the input
     */
    public ParsingClientData(String command) throws IllegalArgumentException {
        checkForIllegalChars(command);
        this.command = command;
        checker = new ClientChecker();
        clients = new ArrayList<>();
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
                    || typeParts[0].trim().equals("delete") || typeParts[0].trim().equals("create") || typeParts[0].trim().equals("list") ) {
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
                    case "get": {
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
                        Client c = checker.getFromTable(columns, values);
                        System.out.println(c.getFirstName());
                        System.out.println("client found");
                        break;
                    }
                    case "list": {
                        int f = command.indexOf('[');
                        int h = command.indexOf(']');
                        String d = command.substring(f+1, h);
                        String[] ss = d.split(", ");
                        int g = command.indexOf('{');
                        int s = command.indexOf('}');
                        String q = command.substring(g+1, s);
                        String[] desc = q.split(", ");
                        if (!d.equals("") && !q.equals("")) {
                            String[] columns = new String[ss.length];
                            String[] values = new String[ss.length];
                            for (int i = 0; i < ss.length; i++) {
                                int y = ss[i].indexOf('=');
                                columns[i] = ss[i].substring(0, y).trim();
                                values[i] = ss[i].substring(y+1).trim();
                            }
                            clients = checker.getListOfClient(columns, values, desc);

                        } else if (d.equals("") && q.equals("")) {
                            clients = checker.getListOfClient(new String[]{}, new String[]{}, new String[]{});

                        }
                        else if (d.equals("") && !q.equals("")) {
                            clients = checker.getListOfClient(new String[]{}, new String[]{}, desc);

                        }
                        else {
                            String[] columns = new String[ss.length];
                            String[] values = new String[ss.length];
                            for (int i = 0; i < ss.length; i++) {
                                int y = ss[i].indexOf('=');
                                columns[i] = ss[i].substring(0, y).trim();
                                values[i] = ss[i].substring(y+1).trim();
                            }
                            clients = checker.getListOfClient(columns, values, new String[]{});
                        }
                    }
                    printList(clients);
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

    private void printList(List<Client> clients) {
        int i = 1;
        for (Client a : clients) {
            System.out.println(i++);
            System.out.println(a.getClietnId() + " client id");
            System.out.println(a.getFirstName() + " first name");
            System.out.println(a.getLastName() + " last name");
            System.out.println(a.getDateOfBirth() + " date of birth");
            System.out.println(" ");
        }
    }
}
