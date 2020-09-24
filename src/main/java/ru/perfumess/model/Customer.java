package ru.perfumess.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
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
