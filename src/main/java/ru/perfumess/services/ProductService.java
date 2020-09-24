package ru.perfumess.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.perfumess.model.product.Product;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);
    Product getOne(Long id);
    void save(Product product);
    void delete(Product product);

}
