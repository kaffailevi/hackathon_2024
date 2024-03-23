//package com.threedumbdevs.springapi.services;
//
//import com.threedumbdevs.springapi.entities.Comment;
//import com.threedumbdevs.springapi.exceptions.NotFoundException;
//import com.threedumbdevs.springapi.repositories.CommentRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class CommentService {
//
//    private CommentRepository commentRepository;
//
//    public List<CommentTO> findAll() {
//        List<Comment> comments = commentRepository.findAll();
//        return comments.stream().map(CommentConverter::convertCommentToTO).toList();
//    }
//
//    public CommentTO findById(Long id) {
//        Optional<Comment> comment = commentRepository.findById(id);
//        if(comment.isPresent()) {
//            return CommentConverter.convertCommentToTO(comment.get());
//        }
//        return null;
//    }
//
//    /*public CommentTO save(CommentTO commentTO) {
//        Comment newComment = CommentConverter.convertTOToComment(commentTO);
//        return CommentConverter.convertCommentToTO(commentRepository.save(newComment));
//    }*/
//
//    public CommentTO update(CommentTO commentTO) {
//        Optional<Comment> comment = commentRepository.findById(commentTO.getId());
//        if (comment.isPresent()) {
//            Comment updatedComment = comment.get();
//            updatedComment.setAnswer(commentTO.getAnswer());
//            updatedComment.setDate(commentTO.getDate());
//            return CommentConverter.convertCommentToTO(commentRepository.save(updatedComment));
//        } else throw new NotFoundException("Comment not found");
//    }
//
//    public CommentTO delete(Long id) {
//        Optional<Comment> comment = commentRepository.findById(id);
//        if (comment.isPresent()) {
//            commentRepository.delete(comment.get());
//            return CommentConverter.convertCommentToTO(comment.get());
//        } else throw new NotFoundException("Comment not found");
//    }
//}
