package com.nikolay.imbirev.connector.checker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandHolder {

    private static CommandHolder holder;
    private List<Command> commandList;

    private CommandHolder() {
        commandList = new ArrayList<>();
        commandList.add(new Command("create client",
                "Add data of the client in such format [name surname date of birth (17/04/2018)]",
                1));
        commandList.add(new Command("delete client",
                "Add data of the client in such format [name surname date of birth (17/04/2018)]",
                2));
        commandList.add(new Command("update client",
                "Add data of the client in such format [name surname] and new data in such format[name = new name, surname = new surname, date of birth = new date of birth (17/04/2018)]",
                3));
        commandList.add(new Command("get client",
                "Add data of the client in such format [name = search name, surname = search surname, date of birth = search date of birth (17/04/2018)]",
                4));
        commandList.add(new Command("list client",
                "Add data of the client in such format [name = search name, surname = search surname, date of birth = search date of birth (17/04/2018)] {sorted columns}",
                5));
    }

    static synchronized CommandHolder getCommandHolder() {
        if (holder == null) {
            holder = new CommandHolder();
        }
        return holder;
    }

    List<Command> getCommandList() {
        return Collections.unmodifiableList(commandList);
    }

    public Command getCommand(String name) {
        for (Command a : commandList) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }
}
