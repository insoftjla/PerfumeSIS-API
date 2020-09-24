package ru.perfumess.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.perfumess.dto.CustomerDto;
import ru.perfumess.model.Customer;
import ru.perfumess.security.userDetails.AuthUser;
import ru.perfumess.security.userDetails.AuthUserFactory;
import ru.perfumess.services.CustomerService;

@Slf4j
@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final CustomerService customerService;

    @Autowired
    public AuthUserDetailsService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerService.getByUsername(username);

        if (customer == null) throw new UsernameNotFoundException("User with username:" + username + "not found");

        AuthUser authUser = AuthUserFactory.create(customer);
//        log.info("METHOD [loadUserByUsername] User (username: {}) SUCCESSFULLY LOAD", username);

        return authUser;
    }

}
