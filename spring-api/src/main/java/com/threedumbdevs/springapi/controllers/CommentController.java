package com.threedumbdevs.springapi.controllers;

import com.threedumbdevs.springapi.TO.CommentTO;
import com.threedumbdevs.springapi.converters.CommentConverter;
import com.threedumbdevs.springapi.entities.Comment;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comment")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    private final CommentService commentService;

    @GetMapping(path = "all")
    public List<CommentTO> getAllComments() {
        return commentService.findAll().stream().map(CommentConverter::convertCommentToTO).toList();
    }

    @GetMapping("/find/{id}")
    public CommentTO getCommentById(@PathVariable Long id) {
        return CommentConverter.convertCommentToTO(commentService.findById(id).orElseThrow(() -> new NotFoundException("Comment not found")));
    }

    @PostMapping(path ="add" )
    public CommentTO addComment(@RequestBody CommentTO commentTO) {
        return CommentConverter.convertCommentToTO(commentService.save(commentTO));
    }

    @PostMapping("/update")
    public CommentTO updateComment(@RequestBody CommentTO commentTO) {
        Comment comment = CommentConverter.convertTOToComment(commentTO);
        try {
            return CommentConverter.convertCommentToTO(commentService.update(comment));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public CommentTO deleteComment(@PathVariable Long id) {
        Comment optionalComment = commentService.delete(id);
        return CommentConverter.convertCommentToTO(optionalComment);
    }
}
