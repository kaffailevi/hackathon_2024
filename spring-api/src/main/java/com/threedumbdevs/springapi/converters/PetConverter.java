package com.threedumbdevs.springapi.converters;

import com.threedumbdevs.springapi.TO.PetTO;
import com.threedumbdevs.springapi.entities.Location;
import com.threedumbdevs.springapi.entities.Pet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class PetConverter {

    public static PetTO convertPetToTO(Pet pet) {
        return new PetTO(pet.getId(), pet.getName(), pet.getBreedName(), pet.getBirthDate(),
                pet.getLastLocation().getId(), pet.getProfilePictureName(),
                pet.getFriends().stream().map(Pet::getId).collect(Collectors.toList()),
                pet.getFavouriteLocations().stream().map(Location::getId).collect(Collectors.toList()));
    }

    public static Pet convertTOToPet(PetTO petTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.parse(petTO.getBirthDate(), formatter);
        return new Pet(petTO.getId(), petTO.getName(), petTO.getBreed(), petTO.getBirthDate(),
                new Location(), petTO.getProfilePictureName(), petTO.getFriends(), petTO.getFavouriteLocations());
    }
}
