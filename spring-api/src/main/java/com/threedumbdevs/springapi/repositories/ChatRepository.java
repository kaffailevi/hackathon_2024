package com.threedumbdevs.springapi.repositories;

import com.threedumbdevs.springapi.entities.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Long> {
    List<Chat> findAll();

    @Query("SELECT c FROM Chat c WHERE c.sender.id = :sender_id AND c.receiver.id = :receiver_id")
    List<Chat> findByUsers(@Param("sender_id") Long user1Id,@Param("receiver_id") Long user2Id);
}
