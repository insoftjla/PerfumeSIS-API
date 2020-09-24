package ru.perfumess.controllers.rest.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perfumess.dto.CustomerDto;
import ru.perfumess.mappers.CustomerMapper;
import ru.perfumess.model.Customer;
import ru.perfumess.model.response.Response;
import ru.perfumess.services.CustomerService;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sort", defaultValue = "firstName") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Customer> customerPage = customerService.findAll(pageable);
        int totalElements = (int) customerPage.getTotalElements();
        List<CustomerDto> list = customerPage
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
        Page<CustomerDto> customersPageDto = new PageImpl<>(list, pageable, totalElements);
        return !customersPageDto.isEmpty()
                ? new ResponseEntity<>(customersPageDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getOne(@PathVariable Long id) {
        Customer customer;
        try {
            customer = customerService.getOne(id);
            return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            log.error("[getOne] Customer (id: {}) NOT FOUND", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public Response update(
            @PathVariable Long id,
            @RequestBody CustomerDto customerDto) {
        try {
            Customer customer = customerService.getOne(id);
            Customer updatedCustomer = customerService.update(customer, customerDto);
            return new Response(customerMapper.toDto(updatedCustomer), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("[getOne] Customer (id: {}) NOT FOUND", id);
            return new Response(HttpStatus.NOT_FOUND);
        } catch (PersistenceException e){
            log.error("[getOne] Exception message: {}", e.getMessage());
            return new Response(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("id") Customer customer) {
        customerService.delete(customer);
    }
}
