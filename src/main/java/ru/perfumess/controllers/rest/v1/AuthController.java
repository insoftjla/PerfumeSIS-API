package ru.perfumess.controllers.rest.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perfumess.dto.AuthenticationRequestDto;
import ru.perfumess.dto.CustomerDto;
import ru.perfumess.mappers.CustomerMapper;
import ru.perfumess.model.Customer;
import ru.perfumess.model.response.Response;
import ru.perfumess.model.shopping.Basket;
import ru.perfumess.security.MyAuthenticationException;
import ru.perfumess.security.cookies.CookieProvider;
import ru.perfumess.services.BasketService;
import ru.perfumess.services.CustomerService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author Sergey Inyakin
 * @version 1.0
 * Base URL /api/v1/auth
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final CookieProvider cookieProvider;
    private final CustomerMapper customerMapper;
    private final BasketService basketService;


    @PostMapping("/login")
    public ResponseEntity<CustomerDto> login(@RequestBody @Valid AuthenticationRequestDto requestDto, HttpServletResponse httpResponse) {
        try {
            String username = requestDto.getUsername();

            Customer customer = customerService.getByUsername(username);
            if (customer == null) {
                throw new MyAuthenticationException("User (username: " + username + ") NOT FOUND");
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            requestDto.getPassword()));
            Cookie tokenCookie = cookieProvider.createToken(username, customer.getRoles());
            httpResponse.addCookie(tokenCookie);

            if (basketService.getByCustomer(customer) == null)
                basketService.save(new Basket(customer));
            log.info("[login] Customer (username: {}) LOGIN", customer.getUsername());
            return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.info("[login] {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<CustomerDto> registration(@RequestBody @Valid Customer customer) {
        if (customerService.getByUsername(customer.getUsername()) != null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        if (customerService.getByEmail(customer.getEmail()) != null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        Customer registeredCustomer = customerService.register(customer);
        log.info("[registration] Registered customer (username {}) SUCCESSFULLY", registeredCustomer.getUsername());
        return new ResponseEntity<>(customerMapper.toDto(registeredCustomer), HttpStatus.OK);
    }
}
