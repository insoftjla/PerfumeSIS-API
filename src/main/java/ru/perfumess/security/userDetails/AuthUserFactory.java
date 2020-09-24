package ru.perfumess.security.userDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.perfumess.model.Customer;
import ru.perfumess.model.Role;
import ru.perfumess.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthUserFactory {

    public AuthUserFactory() {
    }

    public static AuthUser create(Customer customer){

        return new AuthUser(
                customer.getId(),
                customer.getUsername(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPassword(),
                customer.getEmail(),
                customer.getStatus().equals(Status.ACTIVE),
                customer.getUpdated(),
                mapGrantedAuthorities(new ArrayList<>(customer.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapGrantedAuthorities(List<Role> userRole){
        return userRole.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
