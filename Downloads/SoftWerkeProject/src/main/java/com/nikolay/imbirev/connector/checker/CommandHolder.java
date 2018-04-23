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
        commandList.add(new Command("create device",
                "Add data of the device in such format [model producer price date of starting manufactoring (17/04/2018) type color]",
                6));
        commandList.add(new Command("delete device",
                "Add data of the device in such format [model producer date of starting manufactoring (17/04/2018) color type price]",
                7));
        commandList.add(new Command("update device",
                "Add data of the device in such format [model] and new data [model producer date of starting manufactoring (17/04/2018) color type price]",
                8));
        commandList.add(new Command("get device",
                "Add data of the device in such format [model = search model and etc]",
                9));
        commandList.add(new Command("list device",
                "Add data of the device in such format [model = search model, and etc] {sorted columns}",
                10));
        commandList.add(new Command("create sale",
                "Add data of the sale in such format [clientId date of sale (17/04/2018) overall price]",
                11));
        commandList.add(new Command("delete sale",
                "Add data of the sale in such format [clientId date of sale (17/04/2018) overall price]",
                12));
        commandList.add(new Command("update sale",
                "Add data of the sale in such format [clientId price] and new data [clientId = new clientId, and etc]",
                13));
        commandList.add(new Command("get sale",
                "Add data of the sale in such format [clientId = search clientId, and etc]",
                14));
        commandList.add(new Command("list sale",
                "Add data of the sale in such format [clientId = search clientId, and etc] {sorted columns}",
                15));


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
