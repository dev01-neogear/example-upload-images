package com.neogear.example_upload_image.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
  public List<String> folders = new ArrayList<>();

  public static String BASE_DIR = "/images";

  public String uploadImageAvatar(MultipartFile file) throws IOException {
    String fileName = UUID.randomUUID() + ".jpeg";
    String fullFilePath = BASE_DIR + "/avatar" + File.separator + fileName;

    String AVATAR_DIRECTORY = new ClassPathResource("static" + BASE_DIR + "/avatar")
    .getFile().getAbsolutePath();


    String filePath = AVATAR_DIRECTORY + File.separator + fileName;

    try {
      FileOutputStream output = new FileOutputStream(filePath);
      output.write(file.getBytes());
      output.close();
    } catch (Exception e) {
      new IOException("Erro ao salvar imagem");
    }

    return fullFilePath;
  }

}
