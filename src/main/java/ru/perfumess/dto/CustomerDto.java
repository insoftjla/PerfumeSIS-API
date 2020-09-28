package ru.perfumess.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.perfumess.model.Status;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private Date created;
    private Date updated;
    private Status status;
    private String username;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String email;
    private String phoneNumber;
    private List<RoleDto> roles;
    private String photoUrl;
    private List<LocationDto> locations;
}
