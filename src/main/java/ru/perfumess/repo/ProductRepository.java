package ru.perfumess.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.perfumess.model.product.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
}