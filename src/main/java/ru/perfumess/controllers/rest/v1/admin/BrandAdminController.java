package ru.perfumess.controllers.rest.v1.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perfumess.dto.BrandDto;
import ru.perfumess.mappers.BrandMapper;
import ru.perfumess.model.product.Brand;
import ru.perfumess.services.BrandService;

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
        return new ResponseEntity<>(brandMapper.toDto(newBrand), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<BrandDto> update(
            @PathVariable("id") Brand brand,
            @RequestBody String about
    ) {
        brand.setAbout(about);
        brand = brandService.save(brand);
        return new ResponseEntity<>(brandMapper.toDto(brand), HttpStatus.OK);
    }
}
