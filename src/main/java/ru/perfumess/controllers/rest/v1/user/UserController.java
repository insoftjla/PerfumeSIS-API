package ru.perfumess.controllers.rest.v1.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.perfumess.dto.AuthenticationRequestDto;
import ru.perfumess.dto.CustomerDto;
import ru.perfumess.dto.LocationDto;
import ru.perfumess.mappers.CustomerMapper;
import ru.perfumess.mappers.LocationMapper;
import ru.perfumess.model.Customer;
import ru.perfumess.model.Location;
import ru.perfumess.model.response.Response;
import ru.perfumess.services.CustomerService;
import ru.perfumess.services.LocationService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * @author Sergey Inyakin
 * @version 1.0
 * Base URL /api/auth
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<CustomerDto> getAuthCustomerData(Principal principal) {
        Customer customer = customerService.getByUsername(principal.getName());
        if (customer == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> updateAuthData(
            @RequestBody CustomerDto customerDto,
            Principal principal) {
        Customer customer = customerService.getByUsername(principal.getName());
        if (customer == null){
            log.info("[updateAuthData] Customer NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer updatedCustomer = customerService.update(customer, customerDto);
        return new ResponseEntity<>(customerMapper.toDto(updatedCustomer), HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<CustomerDto> updatePassword(
            @RequestBody @Valid AuthenticationRequestDto requestDto,
            Principal principal) {
        String password = requestDto.getPassword();
        if (password == null || password.equals(""))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Customer customer = customerService.getByUsername(principal.getName());
        if (passwordEncoder.matches(password, customer.getPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        customerService.updatePassword(customer, password);
        log.info("[updatePassword] Customer (username: {}) UPDATED PASSWORD", customer.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public Response deleteAuthCustomer(Principal principal) {
        Customer customer = customerService.getByUsername(principal.getName());
        if (customer == null) {
            log.info("[deleteAuthCustomer] Customer NOT FOUND");
            return new Response(HttpStatus.UNAUTHORIZED);
        }
        customerService.delete(customer);
        return new Response(HttpStatus.OK);
    }
}
