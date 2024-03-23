package com.threedumbdevs.springapi.repositories;

import com.threedumbdevs.springapi.entities.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Long> {
    //List<Chat> findAll();

}
