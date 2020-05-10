package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.Buy;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
public class UserDto {
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String name;
    private String role;
    private Long rating;
    @NotNull
    private String contactPhone;
    @NotNull
    private String geoPosition;
    private String email;
    private List<ProductDto> favorites;
    private List<BuyDto> buys;
    private Boolean isDeleted;
    private List<ProductDto> products;
}
