package ru.perfumess.mappers;

import org.mapstruct.Mapper;
import ru.perfumess.dto.BasketDto;
import ru.perfumess.model.shopping.Basket;

@Mapper(componentModel = "spring")
public interface BasketMapper {

    BasketDto toDto(Basket basket);

//    List<ProductDto> toProductDtoList(List<Product> products);
//
//    default Map<ProductDto, Integer> toMapProducts(List<Product> products){
//
//        if (products == null || products.isEmpty()) return null;
//
//        Map<ProductDto, Integer> productDtoMap = new LinkedHashMap<>();
//        List<ProductDto> productDtoList = toProductDtoList(products);
//        productDtoList.forEach(productDto -> {
//            if (!productDtoMap.containsKey(productDto)) {
//                productDtoMap.put(productDto, 1);
//            } else {
//                int count = productDtoMap.get(productDto) + 1;
//                productDtoMap.put(productDto, count);
//            }
//        });
//        return productDtoMap;
//    }

}
