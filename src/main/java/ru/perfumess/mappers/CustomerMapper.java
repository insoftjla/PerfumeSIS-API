package ru.perfumess.mappers;

import org.mapstruct.Mapper;
import ru.perfumess.dto.CustomerDto;
import ru.perfumess.model.Customer;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

//    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toDto(Customer customer);

    Customer toCustomer(CustomerDto customerDto);

    List<CustomerDto> toDtos(List<Customer> customers);


}
