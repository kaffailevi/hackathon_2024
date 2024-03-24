package com.threedumbdevs.springapi.converters;

import com.threedumbdevs.springapi.TO.CommentTO;
import com.threedumbdevs.springapi.entities.Comment;
import com.threedumbdevs.springapi.entities.Post;
import com.threedumbdevs.springapi.entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentConverter {

    public static CommentTO convertCommentToTO(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String strDateTime = comment.getDate().format(formatter);
        return new CommentTO(comment.getId(), comment.getAnswer(), comment.getUser().getId(), comment.getPost().getId(), strDateTime, comment.getUser().getFirstName()+" "+comment.getUser().getLastName());
    }

    public static Comment convertTOToComment(CommentTO commentTO) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime dateTime = LocalDateTime.parse(commentTO.getDate(), formatter);
        return new Comment(-1L, null, null, commentTO.getAnswer(), LocalDateTime.now());
    }
}
