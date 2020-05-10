package ru.itis.flamingo.ecofood.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.entity.Image;

import java.util.List;

public interface MediaService {

    void downloadProfilePhoto(String username, MultipartFile file);

    byte[] getProfilePhoto(String username);

    byte[] getMedia(String name);

    Image downloadImage(MultipartFile multipartFile);

    ProductDto uploadProductImages(Long productId, List<MultipartFile> multipartFileList);

}
