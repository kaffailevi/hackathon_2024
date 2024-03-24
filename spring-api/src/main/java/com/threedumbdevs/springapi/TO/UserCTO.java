package com.threedumbdevs.springapi.TO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCTO {
    private String firstName;
    private String lastName;
    private int age;
    private boolean availableForHire;
    private String password;
    private String email;

}
