package ru.perfumess.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.perfumess.model.Photo;
import ru.perfumess.model.Status;
import ru.perfumess.model.product.Brand;
import ru.perfumess.model.product.ProductType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private Date created;
    private Date updated;
    private Status status;

    private String name;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private Integer value;
    private Integer price;
    private Integer discountedPrice;
    private String description;
    private Brand brand;
    private boolean isPresence;
    private Integer rating;
    private List<PhotoDto> photos;
}
