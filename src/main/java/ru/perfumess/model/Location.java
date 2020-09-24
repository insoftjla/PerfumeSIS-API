package ru.perfumess.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "locations")
public class Location extends BaseEntity{

    private Integer postalCode;
    private String region;
    private String city;
    private String street;
    private String house;
    private String apartment;

    @ManyToMany(mappedBy = "locations")
    private List<Customer> customers;

}
