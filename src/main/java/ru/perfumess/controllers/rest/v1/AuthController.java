package ru.perfumess.controllers.rest.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perfumess.dto.AuthenticationRequestDto;
import ru.perfumess.mappers.CustomerMapper;
import ru.perfumess.model.Customer;
import ru.perfumess.model.response.Response;
import ru.perfumess.security.MyAuthenticationException;
import ru.perfumess.security.cookies.CookieProvider;
import ru.perfumess.services.CustomerService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author Sergey Inyakin
 * @version 1.0
 * Base URL /api/auth
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final CookieProvider cookieProvider;
    private final CustomerMapper customerMapper;


    /**
     * POST "/login"
     * object {
     * username: string
     * password: string
     * }
     * <p>
     * resultCode:
     * 200 - "OK"
     * 401 - "Invalid username or password"
     * payload:
     * customer
     */
    @PostMapping("/login")
    public Response login(@RequestBody @Valid AuthenticationRequestDto requestDto, HttpServletResponse httpResponse) {
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

            return new Response(customerMapper.toDto(customer), HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.info("METHOD [login] {}", e.getMessage());
            return new Response(401, "INVALID USERNAME OR PASSWORD");
        }
    }

    /**
     * POST "/registration"
     * body {
     * username:    string
     * password:     string
     * firstName:   string
     * lastName:    string
     * patronymic:  string
     * email:       string
     * photoUrl:    string
     * locations:   array
     * }
     * <p>
     * resultCode:
     * 200 - "OK"
     * 403 - "Forbidden" // Переданный объект не соответствует ожидаемому
     * 406 - "Username Already Exists"
     * 406 - "Email Already Exists"
     * payload:
     * customer
     */
    @PostMapping("/registration")
    public Response registration(@RequestBody @Valid Customer customer) {
        if (customerService.getByUsername(customer.getUsername()) != null)
            return new Response(406, "Username Already Exists");
        if (customerService.getByEmail(customer.getEmail()) != null)
            return new Response(406, "Email Already Exists");
        Customer registeredCustomer = customerService.register(customer);

        return registeredCustomer != null
                ? new Response(customerMapper.toDto(registeredCustomer), HttpStatus.OK)
                : new Response(HttpStatus.FORBIDDEN);
    }
}
