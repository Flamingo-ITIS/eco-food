package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flamingo.ecofood.domain.entity.Image;
import ru.itis.flamingo.ecofood.domain.repository.UserRepository;
import ru.itis.flamingo.ecofood.exception.MediaServiceException;
import ru.itis.flamingo.ecofood.service.MediaService;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    @Value("${user_data.photo_path}")
    private String dataPath;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void downloadProfilePhoto(String username, MultipartFile file) {
        var fileName = saveFileToDisk(file, dataPath);
        var mainPhoto = new Image()
            .setName(fileName);
        var currentUser = userRepository.findUserByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User with username " + username + " not found"));
        currentUser.setMainPhoto(mainPhoto);
        userRepository.save(currentUser);
    }

    @Override
    public byte[] getProfilePhoto(String username) {
        var user = userRepository.findUserByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User with username " + username + " not found"));
        var profilePhoto = user.getMainPhoto();
        if (profilePhoto == null || profilePhoto.getName() == null) {
            throw new IllegalArgumentException("User has not profile photo");
        }
        try {
            var bis = new BufferedInputStream(new FileInputStream(
                reformatPath(dataPath) + profilePhoto.getName()));
            return IOUtils.toByteArray(bis);
        } catch (IOException e) {
            throw new MediaServiceException(e.getMessage(), e);
        }

    }

    private String saveFileToDisk(MultipartFile file, String dataPath) {
        var uploadDirectory = new File(dataPath);
        if (!uploadDirectory.exists()) {
            var result = uploadDirectory.mkdir();
            if (!result) {
                throw new IllegalArgumentException("Can not create directory " + dataPath);
            }
        }
        var fileName = UUID.randomUUID();
        var fileResolution = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        reformatPath(dataPath);

        var fullName = fileName + fileResolution;
        try {
            file.transferTo(new File(dataPath + fullName));
        } catch (Exception e) {
            throw new MediaServiceException(e.getMessage(), e);
        }

        return fullName;
    }

    private String reformatPath(String dataPath) {
        if (dataPath.charAt(dataPath.length() - 1) != '/') {
            return dataPath + "/";
        }
        return dataPath;
    }


}
