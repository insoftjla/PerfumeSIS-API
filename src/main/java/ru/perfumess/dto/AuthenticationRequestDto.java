package ru.perfumess.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AuthenticationRequestDto {

    @Pattern(regexp = "^(\\w{5,20})$", message = "Does not match the pattern (5-20 \\w)")
    private String username;

    @NotBlank(message = "Not be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d\\S]{8,}$", message = "Does not match the pattern")
    private String password;
}
