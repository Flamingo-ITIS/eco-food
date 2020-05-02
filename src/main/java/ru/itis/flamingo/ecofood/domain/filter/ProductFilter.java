package ru.itis.flamingo.ecofood.domain.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.enums.CategoryName;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ProductFilter {
    private CategoryName category;
}
