package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {
    private Long id;
    private String username;
    private String name;
    private String role;
    private Long rating;
    private String contactPhone;
    private String geoPosition;
}
