package com.threedumbdevs.springapi.repositories;

import com.threedumbdevs.springapi.entities.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {

    List<Location> findAll();
}
