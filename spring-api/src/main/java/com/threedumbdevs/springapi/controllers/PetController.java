package com.threedumbdevs.springapi.controllers;

import com.threedumbdevs.springapi.TO.PetTO;
import com.threedumbdevs.springapi.converters.PetConverter;
import com.threedumbdevs.springapi.entities.Pet;
import com.threedumbdevs.springapi.services.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pet")
@AllArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping(path = "all")
    public List<PetTO> getAllPets() {
        return petService.findAll().stream().map(PetConverter::convertPetToTO).toList();
    }

//    @GetMapping("/{id}")
//    public PetTO getPetById(@PathVariable Long id) {
//        return petService.findById(id);
//    }

//    @PostMapping
//    public Pet createPet(@RequestBody Pet pet) {
//        return petService.createPet(pet);
//    }

//    @PutMapping("/{id}")
//    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet) {
//        return petService.updatePet(id, pet);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletePet(@PathVariable Long id) {
//        petService.deletePet(id);
//    }




}
