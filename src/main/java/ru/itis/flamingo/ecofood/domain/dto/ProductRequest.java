package ru.itis.flamingo.ecofood.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.enums.CategoryName;
import ru.itis.flamingo.ecofood.domain.entity.enums.CountType;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductRequest {
    @NotNull
    private String title;
    private String description;
    @NotNull
    private CategoryName category;
    @NotNull
    private Integer count;
    @NotNull
    private CountType countType;
}
