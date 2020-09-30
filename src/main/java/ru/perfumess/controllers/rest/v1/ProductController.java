package ru.perfumess.controllers.rest.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perfumess.dto.ProductDto;
import ru.perfumess.mappers.ProductMapper;
import ru.perfumess.model.product.Product;
import ru.perfumess.model.response.Response;
import ru.perfumess.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/public/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping()
    public ResponseEntity<Page<ProductDto>> findAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sort", defaultValue = "name") String sort){
        Pageable pageable = PageRequest.of(page-1, size, Sort.by(sort));
        Page<Product> productsPage = productService.findAll(pageable);
        long totalElements = productsPage.getTotalElements();
        List<ProductDto> productDtoList = productMapper.toDtos(productsPage.toList());
        Page<ProductDto> productsDtoPage = new PageImpl<>(productDtoList, pageable, totalElements);
        return !productsDtoPage.isEmpty()
                ? new ResponseEntity<>(productsDtoPage, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getOne(@PathVariable("id") Product product){
        return product != null
                ? new ResponseEntity<>(productMapper.toDto(product), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
