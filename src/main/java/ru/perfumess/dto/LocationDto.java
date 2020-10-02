package ru.perfumess.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.perfumess.model.Status;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private Long id;
    private String createdBy;
    private Date createdDate;
    private String updateBy;
    private Date updatedDate;
    private Status status;

    @NotBlank(message = "Cannot be empty")
    private String country;
    @NotBlank(message = "Cannot be empty")
    private String city;
    @NotBlank(message = "Cannot be empty")
    private String fullAddress;
}


