package com.threedumbdevs.springapi.converters;

import com.threedumbdevs.springapi.TO.PetTO;
import com.threedumbdevs.springapi.entities.Location;
import com.threedumbdevs.springapi.entities.Pet;

public class PetConverter {

    public static PetTO convertPetToTO(Pet pet) {
        return new PetTO(pet.getId(), pet.getName(), pet.getBreedName(), pet.getBirthDate(),
                pet.getLastLocation().getId(), pet.getProfilePictureName(), pet.getFriends().stream().map(PetConverter::convertPetToTO), pet.getFavouriteLocations());
    }

    public static Pet convertTOToPet(PetTO petTO) {
        return new Pet(petTO.getId(), petTO.getName(), petTO.getBreed(), petTO.getBirthDate(),
                new Location(), petTO.getProfilePictureName(), petTO.getFriends(), petTO.getFavouriteLocations());
    }
}
