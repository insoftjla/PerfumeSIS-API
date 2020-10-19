package ru.perfumess.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perfumess.dto.CustomerDto;
import ru.perfumess.model.Customer;
import ru.perfumess.model.Role;
import ru.perfumess.model.Status;
import ru.perfumess.model.shopping.Basket;
import ru.perfumess.repo.CustomerRepository;
import ru.perfumess.repo.RoleRepository;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BasketService basketService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer register(Customer customer) {
        Role roleUser = roleRepository.getByName("ROLE_USER");
        customer.addRole(roleUser);
        customer.setStatus(Status.ACTIVE);
        basketService.save(new Basket(customer));
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Customer getOne(Long id) {
        return customerRepository.getOne(id);
    }

    @Override
    public Customer getByUsername(String username) {
        return customerRepository.getByUsername(username);
    }

    @Override
    public Customer getByEmail(String email) {
        return customerRepository.getByEmail(email);
    }

    @Override
    public Customer update(Customer customer, CustomerDto updateData) {
        if (updateData.getFirstname() != null) customer.setFirstname(updateData.getFirstname());
        if (updateData.getLastname() != null) customer.setLastname(updateData.getLastname());
        if (updateData.getPatronymic() != null) customer.setPatronymic(updateData.getPatronymic());
        if (updateData.getPhotoUrl() != null) customer.setPhotoUrl(updateData.getPhotoUrl());
        return customerRepository.save(customer);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void updatePassword(Customer customer, String password){
        customer.setPassword(passwordEncoder.encode(password));
        customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customer.setStatus(Status.DELETED);
        customerRepository.save(customer);
    }
}
