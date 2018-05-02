package com.nikolay.imbirev.model.entities;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Command {

    private String name;
    private String instructions;

    private int requestCode;

}
