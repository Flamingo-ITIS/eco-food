package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Integer  rating;
}
