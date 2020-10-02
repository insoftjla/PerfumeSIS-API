package ru.perfumess.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.perfumess.model.Status;
import ru.perfumess.model.product.Product;
import ru.perfumess.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getOne(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public Product save(Product product) {
        product.setStatus(Status.ACTIVE);
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
