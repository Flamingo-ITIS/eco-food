package ru.itis.flamingo.ecofood.service;

import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    void downloadProfilePhoto(String username, MultipartFile file);

    byte[] getProfilePhoto(String username);

}
