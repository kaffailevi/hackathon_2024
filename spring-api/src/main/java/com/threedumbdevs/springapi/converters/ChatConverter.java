package com.threedumbdevs.springapi.converters;

import com.threedumbdevs.springapi.TO.ChatTO;
import com.threedumbdevs.springapi.entities.Chat;
import com.threedumbdevs.springapi.entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatConverter {
    public static ChatTO convertChatToTO(Chat chat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String strDateTime = chat.getTimestamp().format(formatter);
        return new ChatTO(chat.getId(), chat.getMessage(), chat.getSender().getId(), chat.getReceiver().getId(), strDateTime);
    }

    public static Chat convertTOToChat(ChatTO chatTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(chatTO.getDate(), formatter);
        return new Chat(chatTO.getId(), new User(), new User(), chatTO.getMessage(), dateTime);
    }
}
