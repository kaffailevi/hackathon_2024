package com.threedumbdevs.springapi.controllers;

import com.threedumbdevs.springapi.TO.CommentTO;
import com.threedumbdevs.springapi.TO.PostTO;
import com.threedumbdevs.springapi.converters.PostConverter;
import com.threedumbdevs.springapi.entities.Post;
import com.threedumbdevs.springapi.entities.User;
import com.threedumbdevs.springapi.exceptions.InternalErrorException;
import com.threedumbdevs.springapi.exceptions.NotAllowedException;
import com.threedumbdevs.springapi.services.ImageService;
import com.threedumbdevs.springapi.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;
    private ImageService imageService;

    private static Integer imageCounter = 0;

    @PostMapping(path = "/post/upload/{id}")
    public PostTO uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {

        PostTO postTO =postService.findById(id);
        try {
            String[] ogFileNameSplitted = file.getOriginalFilename().split("\\.");
            String fileExtension = ogFileNameSplitted[ogFileNameSplitted.length - 1];

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(ImageService.decideMediaType(fileExtension));

            String filename = postTO.getId().toString()+".";
            imageService.savePostImage(file, filename+ fileExtension);
            postTO.setImageUrl(filename+fileExtension);
            return postService.update(postTO);
        } catch (IOException e) {
            throw new InternalErrorException("Error uploading file");
        } catch (IllegalArgumentException e) {
            throw new NotAllowedException("File is empty");
        }
    }

    @GetMapping(path = "/post/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        HttpHeaders headers = new HttpHeaders();
        String fileExtension = imageName.split("\\.")[1];
        headers.setContentType(ImageService.decideMediaType(fileExtension));
        return ResponseEntity.ok().headers(headers).body(imageService.getProfileImageBytes(imageName));
    }

    @PostMapping(path = "/post/add")
    public PostTO addPost(@RequestBody PostTO postTO) {
        return PostConverter.convertPostToTO(postService.save(postTO));
    }

    @GetMapping(path = "/all/{user_id}")
    public List<PostTO> getAllPosts(@PathVariable("user_id") Long user_id) {
        return postService.findAll(user_id);
    }

    @GetMapping(path = "/{post_id}/comments")
    public List<CommentTO> getComments(@PathVariable("post_id") Long post_id) {

        return postService.findCommentsByPostId(post_id);

    }

    @GetMapping(path = "/like/{post_id}/{user_id}")
    public PostTO likePost(@PathVariable("user_id") Long user_id, @PathVariable("post_id") Long post_id) {
         return postService.likePost(post_id,user_id);
    }
    @GetMapping(path = "/dislike/{post_id}/{user_id}")
    public PostTO dislikePost(@PathVariable("user_id") Long user_id, @PathVariable("post_id") Long post_id) {
        return postService.dislike(post_id,user_id);
    }
    @GetMapping(path = "/{id}")
    public PostTO getPost(@PathVariable Long id) {
        return postService.findById(id);
    }


}
