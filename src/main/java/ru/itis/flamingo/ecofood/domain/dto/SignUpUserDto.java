package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SignUpUserDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String contactPhone;
    @NotNull
    private String geoPosition;
    private Boolean isDeleted;
}
