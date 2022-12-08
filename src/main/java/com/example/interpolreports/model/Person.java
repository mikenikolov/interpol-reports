package com.example.interpolreports.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Person {
    private String firstName;
    private String secondName;
    private String birthDate;
    private String entityId;
    private String image;
}
