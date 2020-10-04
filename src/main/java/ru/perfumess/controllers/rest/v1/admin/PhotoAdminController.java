package ru.perfumess.controllers.rest.v1.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import ru.perfumess.dto.PhotoDto;
import ru.perfumess.mappers.PhotoMapper;
import ru.perfumess.model.Photo;
import ru.perfumess.services.ImageSaverService;
import ru.perfumess.services.PhotoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/photos/")
public class PhotoAdminController {

    @Value("${base.url.image}")
    private String baseUrlImage;

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;
    private final ImageSaverService imageSaverService;

    @PostMapping("/upload")
    public ResponseEntity<PhotoDto> upload(
            @RequestParam("file") MultipartFile file) {
        String imageName;
        try {
            imageName = imageSaverService.save(file);
        } catch (MultipartException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Photo photo = new Photo(baseUrlImage + imageName);
        PhotoDto result = photoMapper.toDto(photoService.save(photo));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
