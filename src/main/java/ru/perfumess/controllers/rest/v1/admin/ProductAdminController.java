package ru.perfumess.controllers.rest.v1.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.perfumess.model.product.Product;
import ru.perfumess.model.response.Response;
import ru.perfumess.services.ProductService;

@RestController
@RequestMapping(path = "/api/v1/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Response findAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam(value = "sort", defaultValue = "name") String sort){
        Pageable pageable = PageRequest.of(page-1, size, Sort.by(sort));
        Page<Product> products = productService.findAll(pageable);

        return !products.isEmpty()
                ? new Response(products, HttpStatus.OK)
                : new Response(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public Response getOne(@PathVariable("id") Product product){
        return product != null
                ? new Response(product, HttpStatus.OK)
                : new Response(HttpStatus.NOT_FOUND);
    }
}
