package com.threedumbdevs.springapi.controllers;

import com.threedumbdevs.springapi.TO.ChatTO;
import com.threedumbdevs.springapi.converters.ChatConverter;
import com.threedumbdevs.springapi.entities.Chat;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.services.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chat")
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping(path = "all")
    public List<ChatTO> getAllChats() {
        return chatService.findAll().stream().map(ChatConverter::convertChatToTO).toList();
    }

    @GetMapping(path = "/find/{id}")
    public ChatTO getChatById(@PathVariable Long id) {
        Optional<Chat> opChat = chatService.findById(id);
        if(opChat.isEmpty()) {
            throw new NotFoundException("Chat not found");
        }
        return ChatConverter.convertChatToTO(opChat.get());
    }

    @PostMapping(path = "add")
    public ChatTO addChat(@RequestBody ChatTO chatTO) {
        Chat newChat = ChatConverter.convertTOToChat(chatTO);
        return ChatConverter.convertChatToTO(chatService.save(newChat));
    }

    @PostMapping(path = "/update")
    public ChatTO updateChat(@RequestBody ChatTO chatTO) {
        Chat chat = ChatConverter.convertTOToChat(chatTO);
        try {
            return ChatConverter.convertChatToTO(chatService.update(chat));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ChatTO deleteChat(@PathVariable Long id) {
        Chat chat = chatService.delete(id);
        return ChatConverter.convertChatToTO(chat);
    }

    @GetMapping(path = "/chat_between/{user1_id}/{user2_id}")
    public List<ChatTO> getChatsBetweenUsers(@PathVariable Long user1_id, @PathVariable Long user2_id) {
        return chatService.findByUsers(user1_id, user2_id).stream().map(ChatConverter::convertChatToTO).toList();
    }
}
