package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.entities.Chat;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatService {

    private ChatRepository chatRepository;

    public List<ChatTO> findAll() {
        List<Chat> chats = chatRepository.findAll();
        return chats.stream().map(ChatConverter::convertChatToTO).toList();
    }

    public ChatTO findById(Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if(chat.isPresent()) {
            return ChatConverter.convertChatToTO(chat.get());
        }
        return null;
    }

    /*public ChatTO save(ChatTO chatTO) {
        Chat newChat = ChatConverter.convertTOToChat(chatTO);
        return ChatConverter.convertChatToTO(chatRepository.save(newChat));
    }*/

    public ChatTO delete(Long id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isPresent()) {
            chatRepository.delete(chat.get());
            return ChatConverter.convertChatToTO(chat.get());
        } else throw new NotFoundException("Chat not found");
    }
}
