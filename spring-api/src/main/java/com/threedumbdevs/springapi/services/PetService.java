package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.entities.Pet;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PetService {
    private PetRepository petRepository;

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Optional<Pet> findById(Long id) {
        return petRepository.findById(id);
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet update(Pet pet) {
        Optional<Pet> optionalPet = petRepository.findById(pet.getId());
        if (optionalPet.isEmpty()) {
            throw new NotFoundException("Pet not found");
        }
        Pet updatedPet = optionalPet.get();
        updatedPet.setName(pet.getName());
        updatedPet.setBirthDate(pet.getBirthDate());
        updatedPet.setBreedName(pet.getBreedName());
        return petRepository.save(updatedPet);
    }

    public Pet delete(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        petRepository.delete(optionalPet.orElseThrow(() -> new NotFoundException("Pet not found")));
        return optionalPet.get();
    }

    public List<Pet> findByUserId(Long userId) {
        return petRepository.findByUserId(userId);
    }

    public Pet addFriend(Long petId1, Long petId2) {
        Optional<Pet> optionalPet1 = petRepository.findById(petId1);
        Optional<Pet> optionalPet2 = petRepository.findById(petId2);
        Set<Pet> friends1 = optionalPet1.orElseThrow(()-> new NotFoundException("Pet not found")).getFriends();
        friends1.add(optionalPet2.orElseThrow(()-> new NotFoundException("Pet not found")));
        optionalPet1.get().setFriends(friends1);
        return petRepository.save(optionalPet1.get());
    }
}
