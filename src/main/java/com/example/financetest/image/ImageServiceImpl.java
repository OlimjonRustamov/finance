package com.example.financetest.image;

import com.example.financetest.error.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public HttpEntity<?> uploadImage(MultipartFile image) {
        try {
            Image saved = new Image(image.getContentType(), ImageUtility.compressImage(image.getBytes()));
            saved = imageRepository.save(saved);
            return ResponseEntity.ok(saved);
        } catch (IOException ex) {
            return ResponseEntity.status(400).body(
                    new CustomError("Something went wrong to save image", 400)
            );
        }
    }

    @Override
    public HttpEntity<?> getImage(long id) {
        Optional<Image> optional = imageRepository.findById(id);
        if(optional.isEmpty()) return ResponseEntity.status(404).body(
                new CustomError("Image not found", 404)
        );
        Image dbImage = optional.get();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(dbImage.getContentType()))
                .body(ImageUtility.decompressImage(dbImage.getImage()));
    }
}
