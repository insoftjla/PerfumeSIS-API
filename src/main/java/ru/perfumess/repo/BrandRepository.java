package ru.perfumess.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.perfumess.model.product.Brand;

@RepositoryRestResource
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
