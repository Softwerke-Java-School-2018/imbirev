package com.imbirev.nikolay.model.beans;

import java.sql.Date;
import java.time.LocalDate;

/*
pattern builder for client with some methods for create client parameters
 */
public class ClientBuilder {

    // some variables, necessary for client

    private int clientId;

    private String firstName;
    private String lastName;

    private LocalDate dateOfBirth;

    // standart size of new client is 4 (four obligatory fields)

    private int size;

    /**
     * standart constructor
     * @param size
     */
    public ClientBuilder(int size) {
        this.size = size;
    }

    // there are some methods to add components of new client below
    // each component add 1 to the client size
    // each method param - is the console data

    public void addOrChangeClientId(int clientId) {
        this.clientId = clientId;
        size++;
    }
    public void addOrChangeFirstName(String firstName) {
        this.firstName = firstName;
        size++;
    }
    public void addOrChangeLastName(String lastName) {
        this.lastName = lastName;
        size++;
    }

    // transform date to localdate
    public void addOrChangeLocalDate(Date dateOfBirth) {
        LocalDate date = dateOfBirth.toLocalDate();
        this.dateOfBirth = date;
        size++;
    }

    // here is some get methods for client variables

    public int getClientId() {
        return clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getSize() {
        return size;
    }
}
