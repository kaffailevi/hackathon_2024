package com.threedumbdevs.springapi.repositories;

import com.threedumbdevs.springapi.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();
}
