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



    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank(message = "Firstname must not be empty")
    private String firstname;

    private String lastname;

    private String patronymic;

    @Email(message = "Invalid formed email address")
    private String email;

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
        location.getCustomers().add(this);
    }

    public void removeLocation(Location location){
        if (locations == null) return;
        locations.remove(location);
        location.getCustomers().remove(this);
    }

}
