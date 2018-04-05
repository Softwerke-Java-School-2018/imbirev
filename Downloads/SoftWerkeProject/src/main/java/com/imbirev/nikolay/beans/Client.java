package com.imbirev.nikolay.beans;

import java.time.LocalDate;
import java.util.Date;
/**
 * класс, описывающий сущность клиента
 */
public class Client {

    private int clientId;
    private String fName;
    private String lName;
    private LocalDate dateOfBirth;

    // конструктор для записи в бд
    public Client(int id, String fn, String ln, LocalDate date) {
        clientId = id;
        fName = fn;
        lName = ln;
        dateOfBirth = date;
    }
    // конструктор для получения результатов из бд
    // clientId по идее нам нужно только для поиска по таблице, так что его можно не дергать
    public Client(String fn, String ln, LocalDate date) {
        this(0, fn, ln, date);
    }
    // далее перечислены сеттеры и геттеры

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
