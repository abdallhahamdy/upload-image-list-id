package com.Images.uploadimages.controller;

import com.Images.uploadimages.entity.ImageData;
import com.Images.uploadimages.entity.ImageUploadResponse;
import com.Images.uploadimages.service.ImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageDataController {

    @Autowired
    private ImageDataService imageDataService;

    @PostMapping
    public ResponseEntity<?> uploadImages(@RequestParam("images") List<MultipartFile> files) throws IOException {
        List<ImageUploadResponse> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            ImageUploadResponse response = imageDataService.uploadImage(file);
            responses.add(response);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(responses);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<?>  getImageInfoByName(@PathVariable("name") String name){
        ImageData image = imageDataService.getInfoByImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?>  getImageByName(@PathVariable("name") String name){
        byte[] image = imageDataService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }


}