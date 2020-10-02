package ru.perfumess.model.product;

import lombok.*;
import ru.perfumess.model.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name", callSuper = false)
@Table(name = "brands")
public class Brand extends BaseEntity<String> {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String about;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

}
