package ru.perfumess.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class ImageSaverServiceImpl implements ImageSaverService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String save(MultipartFile file) {
        if (!validateImage(file)) throw new MultipartException("The file must be a image (jpg, jpeg)");

        String uuidFile = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fullFileName = uuidFile + fileName.substring(fileName.indexOf("."));

        try (
                BufferedOutputStream bos =
                        new BufferedOutputStream(new FileOutputStream(uploadPath + "/" + fullFileName))) {
            byte[] bytes = file.getBytes();
            bos.write(bytes);
            return fullFileName;
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new MultipartException("File upload error");
        }
    }

    private boolean validateImage(MultipartFile file) {
        if (file != null
                && file.getContentType() != null
                && (file.getContentType().toLowerCase().equals("image/jpg")
                || file.getContentType().toLowerCase().equals("image/jpeg")))
            return true;
        return false;
    }
}