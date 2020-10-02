package ru.perfumess.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.perfumess.model.Status;
import ru.perfumess.model.product.ProductType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String createdBy;
    private Date createdDate;
    private String updateBy;
    private Date updatedDate;
    private Status status;

    @NotBlank
    private String name;

    private ProductType productType;

    @NotNull
    private Integer value;

    @NotNull
    private Integer price;

    private Integer discountedPrice;

    @NotBlank
    private String description;

    private BrandDto brand;

    @NotNull
    private Boolean isPresence;

    private Integer rating;

    private List<PhotoDto> photos;
}
