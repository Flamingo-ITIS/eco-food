package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.CategoryDto;
import ru.itis.flamingo.ecofood.domain.entity.enums.CategoryName;

public interface CategoryService {

    CategoryDto findByName(CategoryName categoryName);

}
