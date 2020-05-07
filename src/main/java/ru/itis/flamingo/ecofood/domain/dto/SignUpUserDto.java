package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;

@Data
public class SignUpUserDto {
    private String username;
    private String password;
    private String name;
    private String contactPhone;
    private String geoPosition;
}
