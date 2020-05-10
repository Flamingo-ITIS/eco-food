package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.enums.CountType;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductDto {
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    private Double rating;
    @NotNull
    private Integer count;
    @NotNull
    private Double cost;
    @NotNull
    private CategoryDto category;
    @NotNull
    private CountType countType;
    @NotNull
    private CommonSellerDto user;
    private List<ImageDto> images;
}
