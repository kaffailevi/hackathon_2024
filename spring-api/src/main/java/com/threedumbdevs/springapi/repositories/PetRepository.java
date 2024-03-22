package com.threedumbdevs.springapi.repositories;

import com.threedumbdevs.springapi.entities.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long> {
    List<Pet> findAll();
}
