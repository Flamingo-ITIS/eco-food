package ru.itis.flamingo.ecofood.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void saveFile(MultipartFile file, String fileName);

    byte[] getFile(String fileName);

}
