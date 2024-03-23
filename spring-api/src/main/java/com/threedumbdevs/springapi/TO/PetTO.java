package com.threedumbdevs.springapi.TO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class PetTO implements Serializable {

        private Long id;
        private String name;
        private String breed;
        private String birthDate;
        private Long lastLocation_id;
        private String profilePictureName;
        private List<Long> friends;
        private List<Long> favouriteLocations;
}
