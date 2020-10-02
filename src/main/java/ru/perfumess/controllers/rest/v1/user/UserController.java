package ru.perfumess.controllers.rest.v1.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private final LocationService locationService;
    private final LocationMapper locationMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * GET
     * Возвращает авторизованного покупателя.
     * Авторизация проверяется по token_api
     * <p>
     * resultCode:
     * 200 - "OK"
     * 401 - "Token не прошел валидацию"
     * 404 - "Покупатель не найден"
     * payload:
     * customer
     */
    @GetMapping
    public Response getAuthCustomerData(Principal principal) {
        Customer customer = customerService.getByUsername(principal.getName());
        if (customer == null) return new Response(HttpStatus.UNAUTHORIZED);
        CustomerDto customerDto = customerMapper.toDto(customer);
        return customerDto != null
                ? new Response(customerDto, HttpStatus.OK)
                : new Response(HttpStatus.NOT_FOUND);
    }

    /**
     * PUT
     * body {
     * firstName:   string
     * lastName:    string
     * patronymic:  string
     * photoUrl:    string
     * }
     * <p>
     * resultCode:
     * 200 - "OK"
     * 404 - "NOT FOUND"
     * 406 - "Username Already Exists"
     * 406 - "Email Already Exists"
     * payload:
     * customerDto
     */
    @PutMapping
    public Response updateAuthData(
            @RequestBody CustomerDto customerDto,
            Principal principal) {
        Customer customer = customerService.getByUsername(principal.getName());
        Customer updatedCustomer = customerService.update(customer, customerDto);
        return updatedCustomer != null
                ? new Response(customerMapper.toDto(updatedCustomer), HttpStatus.OK)
                : new Response(HttpStatus.NOT_FOUND);
    }

    /**
     * PUT "/password"
     * body {
     * password: string
     * }
     * <p>
     * resultCode:
     * 200 - "OK"
     * 401 - "Password cannot be empty"
     */
    @PutMapping("/password")
    public Response updatePassword(
            @RequestBody @Valid AuthenticationRequestDto requestDto,
            Principal principal) {
        String password = requestDto.getPassword();
        if (password == null || password.equals("")) return new Response(401, "Password cannot be empty");
        Customer customer = customerService.getByUsername(principal.getName());
        if (passwordEncoder.matches(password, customer.getPassword()))
            return new Response(401, "The new password must not be equal to the old one");
        customerService.updatePassword(customer, password);
        return new Response(HttpStatus.OK);
    }

    @PostMapping("/location")
    public Response addLocation(
            @RequestBody @Valid LocationDto locationDto,
            Principal principal) {
        Location location = locationService.save(locationMapper.toLocation(locationDto));
        Customer customer = customerService.getByUsername(principal.getName());
        customer.addLocation(location);
        Customer customerUpdate = customerService.save(customer);
        return new Response(customerMapper.toDto(customerUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/location/{id}")
    public Response removeLocation(
            @PathVariable Long id,
            Principal principal) {
        Customer customer = customerService.getByUsername(principal.getName());
        customer.removeLocation(locationService.getOne(id));
        Customer customerUpdate = customerService.save(customer);
        return new Response(customerMapper.toDto(customerUpdate), HttpStatus.OK);
    }

    /**
     * DELETE
     * <p>
     * resultCode:
     * 200 - "OK"
     * 401 - "UNAUTHORIZED"
     */
    @DeleteMapping
    public Response deleteAuthCustomer(Principal principal) {
        Customer customer = customerService.getByUsername(principal.getName());
        if (customer == null) return new Response(HttpStatus.UNAUTHORIZED);
        customerService.delete(customer);
        return new Response(HttpStatus.OK);
    }
}
