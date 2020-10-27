package ru.perfumess.controllers.rest.v1.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perfumess.dto.ProductDto;
import ru.perfumess.mappers.ProductMapper;
import ru.perfumess.model.product.Product;
import ru.perfumess.services.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/public/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping()
    public ResponseEntity<Page<ProductDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sort", defaultValue = "name") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Product> productsPage = productService.findAll(pageable);
        if (productsPage.isEmpty()){
            log.info("[findAll] Product page IS EMPTY");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        long totalElements = productsPage.getTotalElements();
        List<ProductDto> productDtoList = productMapper.toDtos(productsPage.toList());
        Page<ProductDto> productsDtoPage = new PageImpl<>(productDtoList, pageable, totalElements);
        return new ResponseEntity<>(productsDtoPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOne(@PathVariable("id") Product product) {
        return product != null
                ? new ResponseEntity<>(productMapper.toDto(product), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
