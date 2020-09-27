package ru.perfumess.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.perfumess.dto.CustomerDto;
import ru.perfumess.model.Customer;

import java.util.List;

public interface CustomerService {

    Page<Customer> findAll(Pageable pageable);
    List<Customer> findAll();
    Customer register(Customer customer);
    Customer getOne(Long id);
    Customer getByUsername(String userName);
    Customer getByEmail(String email);
    Customer update(Customer customer, CustomerDto updateData);
    Customer save(Customer customer);

    void updatePassword(Customer customer, String password);

    void delete(Customer customer);

}
