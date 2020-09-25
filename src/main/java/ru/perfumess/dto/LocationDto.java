package ru.perfumess.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.perfumess.model.Status;

import java.util.Date;

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
    private String country;
    private String city;
    private String street;
    private String house;
    private String apartment;
}
