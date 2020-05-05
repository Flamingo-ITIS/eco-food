package ru.itis.flamingo.ecofood.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flamingo.ecofood.service.FileService;

import java.io.IOException;

@Service("dropbox")
@RequiredArgsConstructor
public class DropBoxFileService implements FileService {

    private final DbxClientV2 client;

    @Override
    public void saveFile(MultipartFile file, String fileName) {
        if (file == null) {
            throw  new IllegalArgumentException("File is null");
        }

        try {
            client.files().uploadBuilder("/" + fileName).uploadAndFinish(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getFile(String fileName) {
        try {
            return client.files().download("/" + fileName).getInputStream().readAllBytes();
        } catch (DbxException | IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}
