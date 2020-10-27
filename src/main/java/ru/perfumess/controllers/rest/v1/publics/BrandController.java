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
import ru.perfumess.mappers.BrandMapper;
import ru.perfumess.model.product.Brand;
import ru.perfumess.services.BrandService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public/brands")
public class BrandController {

    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @GetMapping
    public ResponseEntity<Page<BrandDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sort", defaultValue = "name") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Brand> brandPage = brandService.findAll(pageable);
        if (brandPage.isEmpty()){
            log.info("[findAll] Brand page IS EMPTY");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        long totalElements = brandPage.getTotalElements();
        List<BrandDto> brandDtoList = brandMapper.toDtos(brandPage.toList());
        Page<BrandDto> brandDtoPage = new PageImpl<>(brandDtoList, pageable, totalElements);
        return new ResponseEntity<>(brandDtoPage, HttpStatus.OK);
    }
}
