package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.TO.PetTO;
import com.threedumbdevs.springapi.converters.PetConverter;
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

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Optional<PetTO> findById(Long id) {

        return Optional<Pet> pet = petRepository.findById(id);pet.map(PetConverter::convertPetToTO).orElse(null);
    }

    /*public PetTO save(PetTO petTO) {
        Pet newPet = PetConverter.convertTOToPet(petTO);
        return PetConverter.convertPetToTO(petRepository.save(newPet));
    }*/

    public Pet update(Pet pet) {
        Optional<Pet> optionalPet = petRepository.findById(pet.getId());
        if (optionalPet.isPresent()) {
            Pet updatedPet = optionalPet.get();
            updatedPet.setName(pet.getName());
            updatedPet.setBirthDate(pet.getBirthDate());
            updatedPet.setBreedName(pet.getBreedName());
            return petRepository.save(updatedPet);
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
