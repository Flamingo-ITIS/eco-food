package ru.itis.flamingo.ecofood.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommonSellerDto {
    private Long id;
    private String username;
    private String name;
    private Long rating;
    private String contactPhone;
    private String geoPosition;
    private String email;
}
