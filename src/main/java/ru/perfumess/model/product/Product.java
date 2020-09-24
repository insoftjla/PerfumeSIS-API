package ru.perfumess.model.product;

import lombok.*;
import ru.perfumess.model.BaseEntity;
import ru.perfumess.model.Photo;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name", callSuper = false)
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private Integer value;
    private Integer price;
    private Integer discountedPrice;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;
    private boolean isPresence;
    private Integer rating;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Photo> photos;

}
