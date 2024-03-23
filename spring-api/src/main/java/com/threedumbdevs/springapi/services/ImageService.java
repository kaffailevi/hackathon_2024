package com.threedumbdevs.springapi.services;

import com.threedumbdevs.springapi.exceptions.InternalErrorException;
import com.threedumbdevs.springapi.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {


    private final Environment environment;
    private final String PROFILES_PATH;
    private final String POSTS_PATH;

    public ImageService(Environment environment) {
        this.environment = environment;
        POSTS_PATH = environment.getProperty("post.images.path");
        PROFILES_PATH = environment.getProperty("profile.images.path");
    }

    public byte[] getProfileImageBytes(String imageName) {

        Path imagePath = Paths.get(PROFILES_PATH, imageName);

        try {
            if (Files.exists(imagePath))
                return Files.readAllBytes(imagePath);
            else
                throw new NotFoundException("Image not found");
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }


    }

    public byte[] getPostImageBytes(String imageName) {
        Path imagePath = Paths.get(POSTS_PATH, imageName);
        if (Files.exists(imagePath))
            try {
                return Files.readAllBytes(imagePath);
            } catch (IOException e) {
                if(e instanceof NoSuchFileException)
                    throw new NotFoundException("Image not found");
                else
                    throw new InternalErrorException(e);
            }
        else {
            throw new NotFoundException("Image not found");
        }


    }


    public byte[] savePostImage(MultipartFile file, String filename) throws IOException {
        String folderPath = POSTS_PATH;
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        Path path = Paths.get(folderPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        String filePath = folderPath + "/" + filename;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
            return file.getBytes();
        }
    }

    public byte[] saveProfileImage(MultipartFile file, String filename) throws IOException {
        String folderPath = PROFILES_PATH;
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String filePath = folderPath + "/" + filename;
        Path path = Paths.get(folderPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
            return file.getBytes();
        }
    }


}
