package ru.perfumess.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "locations")
public class Location extends BaseEntity<String>{

    @NotBlank(message = "Cannot be empty")
    private String country;

    @NotBlank(message = "Cannot be empty")
    private String city;

    @NotBlank(message = "Cannot be empty")
    private String fullAddress;

    @ManyToMany(mappedBy = "locations")
    private List<Customer> customers;

}
