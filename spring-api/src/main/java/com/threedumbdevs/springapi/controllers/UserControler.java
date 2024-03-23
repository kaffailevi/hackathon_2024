package com.threedumbdevs.springapi.controllers;

import com.threedumbdevs.springapi.TO.UserTO;
import com.threedumbdevs.springapi.converters.UserConverter;
import com.threedumbdevs.springapi.entities.User;
import com.threedumbdevs.springapi.exceptions.InternalErrorException;
import com.threedumbdevs.springapi.exceptions.NotAllowedException;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.services.ImageService;
import com.threedumbdevs.springapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class UserControler {

    private UserService userService;
    private ImageService imageService;
    @GetMapping("/all")
    public List<UserTO> findAll(){
        return userService.findAll().stream().map(UserConverter::convertUserToUserTO).toList();
    }


    private MediaType decideMediaType(String fileExtension) {
        return switch (fileExtension) {
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            case "gif" -> MediaType.IMAGE_GIF;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }

    @GetMapping("/find/{id}")
    public UserTO findUserById(@PathVariable Long id){
        Optional<User> opUser = userService.find(id);
        if(opUser.isEmpty())
            throw new NotFoundException("User not found");
        return UserConverter.convertUserToUserTO(opUser.get());
    }

    @PostMapping( path = "/profile/image/upload/{id}")
    public ResponseEntity<byte[]> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {

        Optional<User> opUser= userService.find(id);
        if(opUser.isEmpty())
            throw new NotFoundException("user not found");
        User user = opUser.get();
        String filename = user.getId().toString();

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

    @GetMapping("/posts/image/{imageName}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable String imageName) {

        HttpHeaders headers = new HttpHeaders();
        String fileExtension = imageName.split("\\.")[1];
        headers.setContentType(decideMediaType(fileExtension));
        return ResponseEntity.ok().headers(headers).body(imageService.getProfileImageBytes(imageName));

    }



}
