package com.threedumbdevs.springapi.converters;

import com.threedumbdevs.springapi.TO.PetTO;
import com.threedumbdevs.springapi.entities.Location;
import com.threedumbdevs.springapi.entities.Pet;
import com.threedumbdevs.springapi.entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.stream.Collectors;

public class PetConverter {

    public static PetTO convertPetToTO(Pet pet) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDate = pet.getBirthDate().format(formatter);
        return new PetTO(pet.getId(), pet.getName(), pet.getBreedName(), birthDate, null, pet.getProfilePictureName(),
                pet.getFriends().stream().map(Pet::getId).collect(Collectors.toList()),
                pet.getFavouriteLocations().stream().map(Location::getId).collect(Collectors.toList()));
    }

    public static Pet convertTOToPet(PetTO petTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.parse(petTO.getBirthDate(), formatter);
        return new Pet(petTO.getId(), new User(), petTO.getName(), petTO.getBreed(), dateTime,
                new Location(), new HashSet<>(), new HashSet<>(), petTO.getProfilePictureName());
    }
}
