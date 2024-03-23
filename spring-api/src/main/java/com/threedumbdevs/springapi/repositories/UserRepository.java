package com.threedumbdevs.springapi.repositories;

import com.threedumbdevs.springapi.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();
}
