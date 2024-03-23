package com.threedumbdevs.springapi.converters;

import com.threedumbdevs.springapi.TO.PostTO;
import com.threedumbdevs.springapi.entities.Post;
import com.threedumbdevs.springapi.entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostConverter {

    public static PostTO convertPostToTO(Post post) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String strDateTime = post.getDate().format(formatter);
        return new PostTO(post.getId(), post.getDescription(), post.getUser().getId(), strDateTime, post.getImageUrl());
    }

    public static Post convertTOToPost(PostTO postTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(postTO.getDate(), formatter);
        return new Post(postTO.getId(), postTO.getDescription(), dateTime, new User(), postTO.getImageUrl());
    }
}
