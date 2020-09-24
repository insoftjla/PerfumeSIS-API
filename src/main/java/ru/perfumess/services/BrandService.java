package ru.perfumess.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.perfumess.model.product.Brand;

public interface BrandService {
    Page<Brand> findAll(Pageable pageable);

    Brand getOne(Long id);

    void save(Brand brand);

    void delete(Brand brand);
}
