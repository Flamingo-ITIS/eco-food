package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.UserBasket;

import java.util.List;

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
    private String email;
    private List<ProductDto> favorites;
    private List<UserBasket> basket;
}
