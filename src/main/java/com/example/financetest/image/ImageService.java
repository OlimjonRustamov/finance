package com.example.financetest.image;

import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    HttpEntity<?> uploadImage(MultipartFile image);

    HttpEntity<?> getImage(long id);

}
