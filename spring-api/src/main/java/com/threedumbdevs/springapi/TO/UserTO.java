package com.threedumbdevs.springapi.TO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private boolean availableForHire;
    private int rating;
    private String profilePictureUrl;
}
