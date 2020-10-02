package ru.perfumess.mappers;

import org.mapstruct.Mapper;
import ru.perfumess.dto.BrandDto;
import ru.perfumess.model.product.Brand;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDto toDto(Brand brand);

    Brand toBrand(BrandDto brandDto);

    List<BrandDto> toDtos(List<Brand> brands);

}
