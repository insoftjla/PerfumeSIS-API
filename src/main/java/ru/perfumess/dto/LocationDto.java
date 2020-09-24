package ru.perfumess.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.perfumess.model.Customer;
import ru.perfumess.model.Status;

import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private Long id;
    private Date created;
    private Date updated;
    private Status status;

    private Integer postalCode;
    private String region;
    private String city;
    private String street;
    private String house;
    private String apartment;
}
