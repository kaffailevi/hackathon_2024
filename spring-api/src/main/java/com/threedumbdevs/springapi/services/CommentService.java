package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.TO.CommentTO;
import com.threedumbdevs.springapi.converters.CommentConverter;
import com.threedumbdevs.springapi.entities.Comment;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.CommentRepository;
import com.threedumbdevs.springapi.repositories.PostRepository;
import com.threedumbdevs.springapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment save(CommentTO comment) {

        Comment newComment = CommentConverter.convertTOToComment(comment);

        newComment.setUser(userRepository.findById(comment.getUserId()).orElseThrow(() -> new NotFoundException("User not found")));
        newComment.setPost(postRepository.findById(comment.getPostId()).orElseThrow(() -> new NotFoundException("Post not found")));

        return commentRepository.save(newComment);
    }

    public Comment update(Comment comment) {
        Optional<Comment> optionalComment = commentRepository.findById(comment.getId());
        if (optionalComment.isPresent()) {
            Comment updatedComment = optionalComment.get();
            updatedComment.setAnswer(comment.getAnswer());
            updatedComment.setDate(comment.getDate());
            return commentRepository.save(updatedComment);
        } else throw new NotFoundException("Comment not found");
    }

    public Comment delete(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        commentRepository.delete(comment.orElseThrow(() -> new NotFoundException("Comment not found")));
        return comment.get();
    }
}
