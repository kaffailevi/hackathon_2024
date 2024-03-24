package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.TO.ChatTO;
import com.threedumbdevs.springapi.converters.ChatConverter;
import com.threedumbdevs.springapi.entities.Chat;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.ChatRepository;
import com.threedumbdevs.springapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatService {

    private ChatRepository chatRepository;
    private UserRepository userRepository;

    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    public Optional<Chat> findById(Long id) {
        return chatRepository.findById(id);
    }

    public Chat save(ChatTO chatTO) {
        Chat chat = ChatConverter.convertTOToChat(chatTO);

        chat.setSender(userRepository.findById(chatTO.getSendUserId()).orElseThrow(() -> new NotFoundException("Sender not found")));
        chat.setReceiver(userRepository.findById(chatTO.getReceiveUserId()).orElseThrow(() -> new NotFoundException("Receiver not found")));

        return chatRepository.save(chat);
    }

    public Chat delete(Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        chatRepository.delete(chat.orElseThrow(() -> new NotFoundException("Chat not found")));
        return chat.get();
    }

    public Chat update(Chat chat) {
        Optional<Chat> optionalChat = chatRepository.findById(chat.getId());
        if (optionalChat.isEmpty()) {
            throw new NotFoundException("Chat not found");
        }
        Chat updatedChat = optionalChat.get();
        updatedChat.setMessage(chat.getMessage());
        updatedChat.setSender(chat.getSender());
        updatedChat.setReceiver(chat.getReceiver());
        updatedChat.setTimestamp(chat.getTimestamp());
        return chatRepository.save(updatedChat);
    }

    public List<Chat> findByUsers(Long user1Id, Long user2Id) {
        return chatRepository.findByUsers(user1Id, user2Id);
    }
}
