package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.TO.PostTO;
import com.threedumbdevs.springapi.entities.Post;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;

    public List<PostTO> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostConverter::convertPostToTO).toList();
    }

    public PostTO findById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            return PostConverter.convertPostToTO(post.get());
        }
        return null;
    }

    /*public PostTO save(PostTO postTO) {
        Post newPost = PostConverter.convertTOToPost(postTO);
        return PostConverter.convertPostToTO(postRepository.save(newPost));
    }*/

    public PostTO update(PostTO postTO) {
        Optional<Post> post = postRepository.findById(postTO.getId());
        if (post.isPresent()) {
            Post updatedPost = post.get();
            updatedPost.setDescription(postTO.getDescription());
            //updatedPost.setDate(postTO.getDate());  ///TODO: Fix this and all date fields
            updatedPost.setImageUrl(postTO.getImageUrl());
            return PostConverter.convertPostToTO(postRepository.save(updatedPost));
        } else throw new NotFoundException("Post not found");
    }

    public PostTO delete(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.delete(post.get());
            return PostConverter.convertPostToTO(post.get());
        } else throw new NotFoundException("Post not found");
    }
}
