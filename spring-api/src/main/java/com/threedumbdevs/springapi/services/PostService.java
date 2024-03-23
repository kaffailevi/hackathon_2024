package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.TO.CommentTO;
import com.threedumbdevs.springapi.TO.PostTO;
import com.threedumbdevs.springapi.converters.CommentConverter;
import com.threedumbdevs.springapi.converters.PostConverter;
import com.threedumbdevs.springapi.entities.Pet;
import com.threedumbdevs.springapi.entities.Post;
import com.threedumbdevs.springapi.entities.User;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.repositories.CommentRepository;
import com.threedumbdevs.springapi.repositories.PetRepository;
import com.threedumbdevs.springapi.repositories.PostRepository;
import com.threedumbdevs.springapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private PetRepository petRepository;
    private CommentRepository commentRepository;

    public List<PostTO> findAll(Long user_id) {
        List<Post> posts = postRepository.findAll();
        List<Pet> pets = petRepository.findAll().stream().filter(pet -> pet.getUser().getId() == user_id).toList();
        List<Pet> petFriends = new ArrayList<>();
        for (Pet pet : pets) {
            petFriends.addAll(pet.getFriends());
        }

        return posts.stream().filter(post ->{
            for (Pet pet : petFriends) {
                if (post.getUser().getId() == pet.getUser().getId()) {
                    return true;
                }
            }
            return false;
        }).map(PostConverter::convertPostToTO).toList();
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

    public List<CommentTO> findCommentsByPostId(Long postId) {
        return commentRepository.findAll().stream().filter(comment -> comment.getPost().getId() == postId).map(CommentConverter::convertCommentToTO).toList();
    }

    public PostTO likePost(Long post_id, Long userId) {
        Optional<Post> opPost =  postRepository.findById(post_id);
        if (opPost.isPresent()) {
            Post post = opPost.orElseThrow(() -> new NotFoundException("Post not found"));
            Optional<User> opUser = userRepository.findById(userId);
            Set<User> likes= post.getLikes();
            likes.add(opUser.orElseThrow(() -> new NotFoundException("User not found")));
            post.setLikes(likes);
           return  PostConverter.convertPostToTO(postRepository.save(post));
        } else {
            throw new NotFoundException("Post not found");
        }

    }

    public PostTO dislike(Long postId, Long userId) {
        Optional<Post> opPost =  postRepository.findById(postId);
        if (opPost.isPresent()) {
            Post post = opPost.orElseThrow(() -> new NotFoundException("Post not found"));
            Optional<User> opUser = userRepository.findById(userId);
            Set<User> likes= post.getLikes();
            likes.remove(opUser.orElseThrow(() -> new NotFoundException("User not found")));
            post.setLikes(likes);
            return  PostConverter.convertPostToTO(postRepository.save(post));
        } else {
            throw new NotFoundException("Post not found");
        }
    }
}
