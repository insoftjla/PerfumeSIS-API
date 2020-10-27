package ru.perfumess.controllers.rest.v1.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.perfumess.dto.BrandDto;
import ru.perfumess.dto.PhotoDto;
import ru.perfumess.mappers.BrandMapper;
import ru.perfumess.mappers.PhotoMapper;
import ru.perfumess.model.Photo;
import ru.perfumess.model.product.Brand;
import ru.perfumess.services.BrandService;
import ru.perfumess.services.PhotoService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @GetMapping
    public ResponseEntity<Page<PhotoDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sort", defaultValue = "createdDate") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Photo> photoPage = photoService.findAll(pageable);
        if (photoPage.isEmpty()){
                log.info("[findAll] Photo page IS EMPTY");
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        long totalElements = photoPage.getTotalElements();
        List<PhotoDto> photoDtoList = photoMapper.toDtos(photoPage.toList());
        Page<PhotoDto> photoDtoPage = new PageImpl<>(photoDtoList, pageable, totalElements);
        return new ResponseEntity<>(photoDtoPage, HttpStatus.OK);
    }
}
