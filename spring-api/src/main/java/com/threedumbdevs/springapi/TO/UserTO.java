package com.threedumbdevs.springapi.TO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;

@Data
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private boolean availableForHire;
    private int rating;
    private String profilePictureUrl;
}
