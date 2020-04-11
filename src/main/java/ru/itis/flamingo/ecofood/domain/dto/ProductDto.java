package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.enums.CountType;

@Data
@Accessors(chain = true)
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Double rating;
    private Integer count;
    private CategoryDto category;
    private CountType countType;
    private CommonSellerDto user;
}
