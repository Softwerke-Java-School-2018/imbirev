package com.nikolay.imbirev.connector.checker;

public class Command {

    private String name;

    private int requestCode;

    public Command(String name, int requestCode) {
        this.name = name;
        this.requestCode = requestCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }
}
