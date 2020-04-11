package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flamingo.ecofood.exception.MediaServiceException;
import ru.itis.flamingo.ecofood.service.FileService;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LocalStorageFileServiceImpl implements FileService {

    @Value("${user_data.photo_path}")
    private String dataPath;

    public void saveFile(MultipartFile file, String fileName) {
        if (file == null) {
            throw  new IllegalArgumentException("File is null");
        }

        var uploadDirectory = new File(dataPath);
        if (!uploadDirectory.exists()) {
            var result = uploadDirectory.mkdir();
            if (!result) {
                throw new IllegalArgumentException("Can not create directory " + dataPath);
            }
        }

        var normalizeDataPath = reformatPath(dataPath);

        try {
            file.transferTo(new File(normalizeDataPath + fileName));
        } catch (Exception e) {
            throw new MediaServiceException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] getFile(String fileName) {
        try {
            var bis = new BufferedInputStream(new FileInputStream(
                reformatPath(dataPath) + fileName));
            return IOUtils.toByteArray(bis);
        } catch (IOException e) {
            throw new MediaServiceException(e.getMessage(), e);
        }
    }


    private String reformatPath(String dataPath) {
        if (dataPath.charAt(dataPath.length() - 1) != '/') {
            return dataPath + "/";
        }
        return dataPath;
    }

}
