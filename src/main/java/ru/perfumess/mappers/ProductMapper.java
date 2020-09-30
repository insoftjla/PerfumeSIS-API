package ru.perfumess.mappers;

import org.mapstruct.Mapper;
import ru.perfumess.dto.ProductDto;
import ru.perfumess.model.product.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toProduct(ProductDto productDto);

    List<ProductDto> toDtos(List<Product> products);

}
