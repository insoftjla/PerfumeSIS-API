package ru.perfumess.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "email", callSuper = false)
@Table(name = "customers")
public class Customer extends BaseEntity {



    @NotBlank(message = "Not be empty")
    @Pattern(regexp = "^(\\w{5,20})$", message = "Does not match the pattern (5-20 \\w)")
    private String username;

    @NotBlank(message = "Not be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d\\S]{8,}$", message = "Does not match the pattern")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "Not be empty")
    private String firstname;

    @NotBlank(message = "Not be empty")
    private String lastname;

    @NotBlank(message = "Not be empty")
    private String patronymic;

    @NotBlank(message = "Not be empty")
    @Email(message = "Invalid formed email address")
    private String email;

    @NotBlank(message = "Not be empty")
    @Pattern(regexp = "^(\\+?)(0|[1-9][0-9]{10})$", message = "Does not match the pattern")
    private String phoneNumber;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "customers_roles",
            joinColumns = @JoinColumn(name = "customers_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Role> roles;

    private String photoUrl;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "customers_locations",
            joinColumns = @JoinColumn(name = "customers_id"),
            inverseJoinColumns = @JoinColumn(name = "locations_id"))
    private List<Location> locations;


    public void addRole(Role role) {
        if (roles == null) roles = new ArrayList<>();
        roles.add(role);
        role.getCustomers().add(this);
    }

    public void removeRole(Role role) {
        if (roles == null) return;
        roles.remove(role);
        role.getCustomers().remove(this);
    }

    public void addLocation(Location location){
        if (locations == null) locations = new ArrayList<>();
        locations.add(location);
        if (location.getCustomers() == null) location.setCustomers(new ArrayList<>());
        location.getCustomers().add(this);
    }

    public void removeLocation(Location location){
        if (locations == null) return;
        locations.remove(location);
        if (location.getCustomers() == null) return;
        location.getCustomers().remove(this);
    }

}
