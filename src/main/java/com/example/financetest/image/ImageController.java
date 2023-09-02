package com.example.financetest.image;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    HttpEntity<?> uploadImage(@RequestParam("image") MultipartFile image) {
        return imageService.uploadImage(image);
    }

    @GetMapping("/view/{image_id}")
    HttpEntity<?> uploadImage(@PathVariable("image_id") long id) {
        return imageService.getImage(id);
    }
}
