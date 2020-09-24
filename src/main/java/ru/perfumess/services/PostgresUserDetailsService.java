//package ru.perfumess.services;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.perfumess.model.Customer;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class PostgresUserDetailsService implements UserDetailsService {
//
//    private final CustomerService customerService;
//
//    public PostgresUserDetailsService(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        Customer customer = customerService.getByUsername(s);
//
//        if (customer == null) throw new UsernameNotFoundException("User not found");
//
//        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
//
//        return new User(customer.getUsername(), customer.getPassword(), authorities);
//    }
//}
