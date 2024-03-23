package com.threedumbdevs.springapi.controllers;

import com.threedumbdevs.springapi.TO.PostTO;
import com.threedumbdevs.springapi.converters.PostConverter;
import com.threedumbdevs.springapi.entities.Post;
import com.threedumbdevs.springapi.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    @PostMapping(path = "/post/add")
    public PostTO addPost(@RequestBody PostTO postTO, @RequestParam("file") MultipartFile file) {

        postTO = PostConverter.convertPostToTO(postService.save(postTO));


        String filename = postTO.getId().toString();

        try {
            String[] ogFileNameSplitted = file.getOriginalFilename().split("\\.");
            String fileExtension = ogFileNameSplitted[ogFileNameSplitted.length - 1];

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(decideMediaType(fileExtension));

            return ResponseEntity.ok().headers(headers).body(imageService.saveProfileImage(file, filename+ fileExtension));
        } catch (IOException e) {
            throw new InternalErrorException("Error uploading file");
        } catch (IllegalArgumentException e) {
            throw new NotAllowedException("File is empty");
        }
    }
    @PostMapping( path = "/post/image/upload/{id}")
    public ResponseEntity<byte[]> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {


    }


}
