package ru.perfumess.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.perfumess.model.Status;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {

    private Long id;
    private String createdBy;
    private Date createdDate;
    private String updateBy;
    private Date updatedDate;
    private Status status;

    @NotBlank
    private String name;

    @NotBlank
    private String about;

    private Set<ProductDto> products;
}
