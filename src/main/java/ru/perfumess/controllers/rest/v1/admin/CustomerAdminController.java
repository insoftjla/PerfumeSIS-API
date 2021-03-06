package ru.perfumess.controllers.rest.v1.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perfumess.dto.CustomerDto;
import ru.perfumess.mappers.CustomerMapper;
import ru.perfumess.model.Customer;
import ru.perfumess.services.CustomerService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/customers")
public class CustomerAdminController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "firstname") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Customer> customerPage = customerService.findAll(pageable);
        long totalElements = customerPage.getTotalElements();
        List<CustomerDto> customerDtoList = customerMapper.toDtos(customerPage.toList());
        Page<CustomerDto> customersPageDto = new PageImpl<>(customerDtoList, pageable, totalElements);
        return !customersPageDto.isEmpty()
                ? new ResponseEntity<>(customersPageDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getOne(
            @PathVariable("id") Customer customer) {
        if (customer == null) {
            log.info("[getOne] Customer NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(
            @PathVariable("id") Customer customer,
            @RequestBody CustomerDto customerDto) {

        if (customer == null) {
            log.info("[update] Customer NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer updatedCustomer = customerService.update(customer, customerDto);
        return new ResponseEntity<>(customerMapper.toDto(updatedCustomer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(
            @PathVariable("id") Customer customer) {
        customerService.delete(customer);
    }
}
