package com.imbirev.nikolay.model.beans;

import java.time.LocalDate;
/**
 * client entity
 */
public class Client {

    // obliatory fields for client entity

    private int clientId;

    private String fName;
    private String lName;

    private LocalDate dateOfBirth;

    // constructor to add client into database or to get it from there

    /**
     *
     * @param clientBuilder - is the builder of client, which can be used to add/change information from the console/database
     */
    public Client(ClientBuilder clientBuilder) {
        clientId = clientBuilder.getClientId();
        fName = clientBuilder.getFirstName();
        lName = clientBuilder.getLastName();
        dateOfBirth = clientBuilder.getDateOfBirth();
    }

    public int getClientId() {
        return clientId;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}