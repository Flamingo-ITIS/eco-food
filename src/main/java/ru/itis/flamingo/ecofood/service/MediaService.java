package ru.itis.flamingo.ecofood.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.flamingo.ecofood.domain.entity.Image;

public interface MediaService {

    void downloadProfilePhoto(String username, MultipartFile file);

    byte[] getProfilePhoto(String username);

    Image downloadImage(MultipartFile multipartFile);

}
