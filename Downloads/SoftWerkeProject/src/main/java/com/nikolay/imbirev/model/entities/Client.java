package com.nikolay.imbirev.model.entities;

import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Value
@ToString(exclude = "clietnId") // call super = false
public class Client extends BaseEntity {

    private String clietnId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

}