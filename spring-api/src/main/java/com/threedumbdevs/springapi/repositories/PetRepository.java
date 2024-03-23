package com.threedumbdevs.springapi.repositories;

import com.threedumbdevs.springapi.entities.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long> {
    List<Pet> findAll();

    @Query("SELECT p FROM Pet p WHERE p.user.id = :user_id")
    List<Pet> findByUserId(@Param("user_id") Long userId);
}
