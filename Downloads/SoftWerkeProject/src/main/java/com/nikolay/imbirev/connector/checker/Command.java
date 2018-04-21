package com.nikolay.imbirev.connector.checker;

public class Command {

    private String name;
    private String instructions;

    private int requestCode;

    Command(String name, String instructions, int requestCode) {
        this.name = name;
        this.requestCode = requestCode;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    int getRequestCode() {
        return requestCode;
    }

    public String getInstructions() {
        return instructions;
    }

}
