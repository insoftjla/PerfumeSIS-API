package ru.perfumess.controllers.rest.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import ru.perfumess.model.Photo;
import ru.perfumess.model.response.Response;
import ru.perfumess.services.ImageSaverService;
import ru.perfumess.services.PhotoService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/photos/")
public class PhotoController {

    @Value("${base.url.image}")
    private String baseUrlImage;

    private final PhotoService photoService;
    private final ImageSaverService imageSaverService;

    @PostMapping("/upload")
    public Response upload(
            @RequestParam("file") MultipartFile file) {
        String imageName;
        try {
            imageName = imageSaverService.save(file);
        } catch (MultipartException e) {
            return new Response(400, e.getMessage());
        }
        Photo photo = new Photo(baseUrlImage + imageName);
        Photo result = photoService.save(photo);
        return new Response(result, HttpStatus.OK);
    }

}
