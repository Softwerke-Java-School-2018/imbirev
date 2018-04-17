package com.nikolay.imbirev.model.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Client {

    private String clietnId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Client(String clietnId, String firstName, String lastName, LocalDate dateOfBirth) {
        this.clietnId = clietnId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public static class ClientBuilder {

        private String clietnId;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;

        public ClientBuilder setClientId() {
            UUID id = UUID.randomUUID();
            StringBuilder clientId = new StringBuilder().append("cli").append(id.toString());
            this.clietnId = clientId.toString();
            return this;
        }

        public ClientBuilder setClientId(String id) {
            this.clietnId = id;
            return this;
        }

        public ClientBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public ClientBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public ClientBuilder setDateofBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Client build() {
            return new Client(clietnId, firstName, lastName, dateOfBirth);
        }

    }


    public String getClietnId() {
        return clietnId;
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
}
