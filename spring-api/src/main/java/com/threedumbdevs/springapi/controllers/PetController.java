package com.threedumbdevs.springapi.controllers;

import com.threedumbdevs.springapi.TO.PetTO;
import com.threedumbdevs.springapi.converters.PetConverter;
import com.threedumbdevs.springapi.entities.Pet;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.services.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pet")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping(path = "all")
    public List<PetTO> getAllPets() {
        return petService.findAll().stream().map(PetConverter::convertPetToTO).toList();
    }

    @GetMapping("/find/{id}")
    public PetTO getPetById(@PathVariable Long id) {
        Optional<Pet> opPet = petService.findById(id);
        if(opPet.isEmpty()) {
            throw new NotFoundException("Pet not found");
        }
        return PetConverter.convertPetToTO(opPet.get());
    }

    @PostMapping(path ="add" )
    public PetTO addPet(@RequestBody PetTO petTO) {
        Pet newPet = PetConverter.convertTOToPet(petTO);
        return PetConverter.convertPetToTO(petService.save(newPet));
    }

    @PostMapping("/update")
    public PetTO updatePet(@RequestBody PetTO petTO) {
        Pet pet = PetConverter.convertTOToPet(petTO);
        try {
            return PetConverter.convertPetToTO(petService.update(pet));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public PetTO deletePet(@PathVariable Long id) {
        Pet optionalPet = petService.delete(id);
        return PetConverter.convertPetToTO(optionalPet);
    }

    ///TODO user_id -> pet list
    @GetMapping(path = "/user/{user_id}")
    public List<PetTO> getPetsByUserId(@PathVariable Long user_id) {
        return petService.findByUserId(user_id).stream().map(PetConverter::convertPetToTO).toList();
    }
}
