package com.threedumbdevs.springapi.controllers;

import com.sun.net.httpserver.Headers;
import com.threedumbdevs.springapi.exceptions.InternalErrorException;
import com.threedumbdevs.springapi.exceptions.NotAllowedException;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import com.threedumbdevs.springapi.services.ImageService;
import com.threedumbdevs.springapi.services.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    private TokenService tokenService;
    private ImageService imageService;

//    @GetMapping("/hello")
//    public String test() {
//        return tokenService.createToken("test","test@mail.com");
//    }


//
//    @GetMapping("/posts/image/{imageName}")
//    public ResponseEntity<byte[]> getProfileImage(@PathVariable String imageName) {
//
//            HttpHeaders headers = new HttpHeaders();
//            String fileExtension = imageName.split("\\.")[1];
//            headers.setContentType(decideMediaType(fileExtension));
//            return ResponseEntity.ok().headers(headers).body(imageService.getPostImageBytes(imageName));
//
//    }







}
