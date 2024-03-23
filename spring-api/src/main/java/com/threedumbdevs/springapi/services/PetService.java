package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.TO.PetTO;
import com.threedumbdevs.springapi.entities.Pet;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetService {
    private PetRepository petRepository;

    public List<PetTO> findAll() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream().map(PetConverter::convertPetToTO).toList();
    }

    public PetTO findById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isPresent()) {
            return PetConverter.convertPetToTO(pet.get());
        }
        return null;
    }

    /*public PetTO save(PetTO petTO) {
        Pet newPet = PetConverter.convertTOToPet(petTO);
        return PetConverter.convertPetToTO(petRepository.save(newPet));
    }*/

    public PetTO update(PetTO petTO) {
        Optional<Pet> pet = petRepository.findById(petTO.getId());
        if (pet.isPresent()) {
            Pet updatedPet = pet.get();
            updatedPet.setName(petTO.getName());
            updatedPet.setBirthDate(petTO.getBirthDate());
            updatedPet.setBreedName(petTO.getBreed());
            return PetConverter.convertPetToTO(petRepository.save(updatedPet));
        } else throw new NotFoundException("Pet not found");
    }

    public PetTO delete(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            petRepository.delete(pet.get());
            return PetConverter.convertPetToTO(pet.get());
        } else throw new NotFoundException("Pet not found");
    }
}
