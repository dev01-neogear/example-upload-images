package com.neogear.example_upload_image.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.neogear.example_upload_image.services.ImageService;

@RestController
@RequestMapping("/files")
public class ImageController {
  @Autowired
  private ImageService imageService; 
  
  @PostMapping("/avatar")
    public ResponseEntity uploadAvatar(@RequestParam(name = "image") MultipartFile image) throws IOException {
      try {
        // User user = getAuth();

        // if (user == null) {
        //   throw new ResponseStatusException(
        //     HttpStatus.UNAUTHORIZED, 
        //     "Você deve estar autenticado."
        //   );
        // }

        if (image.getSize() > 512000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Imagem muito pesada, tente enviar no tamanho menor ou igual a 512kb");
        }

        if (!image.getOriginalFilename().endsWith("jpeg") && !image.getOriginalFilename().endsWith("jpg")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato não suportado, tente enviar no formato JPEG ou JPG");
        }

        String path = imageService.uploadImageAvatar(image);

        // userService.uploadAvatar(user.getId(), path);

        // ImageDTO imgDTO = new ImageDTO(path);
        
        return new ResponseEntity(path, HttpStatus.CREATED);
      } catch (ResponseStatusException e) {
        throw new ResponseStatusException(e.getStatus(), e.getReason());
      }
        
    }

}
