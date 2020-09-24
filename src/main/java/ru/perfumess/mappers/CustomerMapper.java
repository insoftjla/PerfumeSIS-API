package ru.perfumess.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.perfumess.dto.CustomerDto;
import ru.perfumess.model.Customer;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toDto(Customer customer);

    Customer toCustomer(CustomerDto customerDto);


}
