package com.imbirev.nikolay.beans;

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

    private void addOrChangeClientId(int clientId) {
        this.clientId = clientId;
        size++;
    }
    private void addOrChangeFirstName(String firstName) {
        this.firstName = firstName;
        size++;
    }
    private void addOrChangeLastName(String lastName) {
        this.lastName = lastName;
        size++;
    }

    private void addOrChangeLocalDate(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
