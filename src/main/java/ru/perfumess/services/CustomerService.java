package ru.perfumess.services;

import ru.perfumess.dto.CustomerDto;
import ru.perfumess.model.Customer;

public interface CustomerService extends BaseService<Customer> {

    Customer register(Customer customer);

    Customer getByUsername(String userName);

    Customer getByEmail(String email);

    Customer update(Customer customer, CustomerDto updateData);

    void updatePassword(Customer customer, String password);

}
