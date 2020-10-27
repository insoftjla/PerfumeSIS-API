package ru.perfumess.controllers.rest.v1.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perfumess.dto.ProductDto;
import ru.perfumess.mappers.ProductMapper;
import ru.perfumess.model.Photo;
import ru.perfumess.model.product.Brand;
import ru.perfumess.model.product.Product;
import ru.perfumess.services.ProductService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/admin/products")
public class ProductAdminController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductDto> newProduct(
            @RequestBody @Valid ProductDto productDto,
            @RequestParam("brandId") Brand brand,
            @RequestParam("photoId") Photo photo
    ) {
        if (brand == null || photo == null) {
            log.info("[newProduct] Brand or Photo NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product newProduct = productMapper.toProduct(productDto);
        newProduct.addBrand(brand);
        newProduct.addPhoto(photo);
        newProduct = productService.save(newProduct);
        return new ResponseEntity<>(productMapper.toDto(newProduct), HttpStatus.OK);
    }

    @PutMapping("/{id}/brand/{brandId}")
    public ResponseEntity<ProductDto> setBrand(
            @PathVariable("id") Product product,
            @PathVariable("brandId") Brand brand
    ) {
        if (product == null || brand == null) {
            log.info("[setBrand] Product or Brand NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setBrand(brand);
        Product updatedProduct = productService.save(product);
        return new ResponseEntity<>(productMapper.toDto(updatedProduct), HttpStatus.OK);
    }

    @PutMapping("/{id}/photo/{photoId}")
    public ResponseEntity<ProductDto> addPhoto(
            @PathVariable("id") Product product,
            @PathVariable("photoId") Photo photo
    ) {
        if (product == null || photo == null) {
            log.info("[setBrand] Product or Photo NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.addPhoto(photo);
        product = productService.save(product);
        return new ResponseEntity<>(productMapper.toDto(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/photo/{photoId}")
    public ResponseEntity<ProductDto> deletePhoto(
            @PathVariable("id") Product product,
            @PathVariable("photoId") Photo photo
    ) {
        if (product == null || photo == null) {
            log.info("[setBrand] Product or Photo NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.deletePhoto(photo);
        product = productService.save(product);
        return new ResponseEntity<>(productMapper.toDto(product), HttpStatus.OK);
    }

}
