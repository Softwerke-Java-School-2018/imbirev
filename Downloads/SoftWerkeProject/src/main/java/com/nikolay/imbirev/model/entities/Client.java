package com.nikolay.imbirev.model.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Value
public class Client {

    private String clietnId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

}
