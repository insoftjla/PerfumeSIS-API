package ru.perfumess.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "name", callSuper = false)
@Table(name = "roles")
public class Role extends BaseEntity {

    private String name;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers;
}
