package ru.perfumess.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface ImageSaverService {
    String save(MultipartFile file);
}
