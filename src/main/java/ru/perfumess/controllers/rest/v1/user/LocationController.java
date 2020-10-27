package ru.perfumess.controllers.rest.v1.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
public class LocationController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final LocationService locationService;
    private final LocationMapper locationMapper;

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
    public ResponseEntity<CustomerDto> removeLocation(
            @PathVariable("id") Location location,
            Principal principal) {
        Customer customer = customerService.getByUsername(principal.getName());
        if (location == null || customer == null) {
            log.info("[removeLocation] Location or Customer NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.removeLocation(location);
        Customer customerUpdate = customerService.save(customer);
        return new ResponseEntity<>(customerMapper.toDto(customerUpdate), HttpStatus.OK);
    }
}
