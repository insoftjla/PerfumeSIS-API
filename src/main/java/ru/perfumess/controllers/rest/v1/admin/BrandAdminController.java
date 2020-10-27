package ru.perfumess.controllers.rest.v1.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perfumess.dto.BrandDto;
import ru.perfumess.mappers.BrandMapper;
import ru.perfumess.model.product.Brand;
import ru.perfumess.services.BrandService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/brands")
public class BrandAdminController {

    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @PostMapping
    public ResponseEntity<BrandDto> save(
            @RequestBody BrandDto brandDto
    ) {
        Brand newBrand = brandMapper.toBrand(brandDto);
        newBrand = brandService.save(newBrand);
        return newBrand != null
                ? new ResponseEntity<>(brandMapper.toDto(newBrand), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/{id}")
    private ResponseEntity<BrandDto> update(
            @PathVariable("id") Brand brand,
            @RequestBody String about
    ) {
        if (brand == null || about == null){
            log.info("[update] Brand NOT FOUND or obout is NULL");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        brand.setAbout(about);
        brand = brandService.save(brand);
        return new ResponseEntity<>(brandMapper.toDto(brand), HttpStatus.OK);
    }
}
