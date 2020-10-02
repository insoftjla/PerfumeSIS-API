package ru.perfumess.model.product;

import lombok.*;
import ru.perfumess.model.BaseEntity;
import ru.perfumess.model.Photo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "products")
public class Product extends BaseEntity<String> {

    @EqualsAndHashCode.Include
    private String name;

    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @EqualsAndHashCode.Include
    private Integer value;

    private Integer price;

    private Integer discountedPrice;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    private Boolean isPresence;
    private Integer rating;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "products_photos",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "photos_id"))
    private List<Photo> photos;

    public void addPhoto(Photo photo){
        if (photos == null) photos = new ArrayList<>();
        photos.add(photo);
    }

    public void deletePhoto(Photo photo){
        if (photos == null) return;
        photos.remove(photo);
    }

    public void addBrand(Brand brand){
        if (this.brand != null) brand.getProducts().remove(this);
        this.brand = brand;
        brand.getProducts().add(this);
    }

}
