package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.TO.PostTO;
import com.threedumbdevs.springapi.converters.PostConverter;
import com.threedumbdevs.springapi.entities.Post;
import com.threedumbdevs.springapi.entities.User;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.PostRepository;
import com.threedumbdevs.springapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public List<PostTO> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostConverter::convertPostToTO).toList();
    }

    public PostTO findById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.map(PostConverter::convertPostToTO).orElse(null);
    }

   public Post save(PostTO postTO) {
       Post newPost = PostConverter.convertTOToPost(postTO);
//       private String date;
//       private Long id;
//       private String description;
//       private String imageUrl;
//       private Long userId;

       newPost.setDate(LocalDateTime.now());
       Optional<User> optionalUser = userRepository.findById(postTO.getUserId());

       newPost.setUser(optionalUser.orElseThrow(() -> new NotFoundException("User not found")));



       return postRepository.save(newPost);
   }

    public PostTO update(PostTO postTO) {
        Optional<Post> post = postRepository.findById(postTO.getId());
        if (post.isPresent()) {
            Post updatedPost = post.get();
            updatedPost.setDescription(postTO.getDescription());
            updatedPost.setDate(LocalDateTime.parse(postTO.getDate()));  ///TODO: Fix this and all date fields
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
