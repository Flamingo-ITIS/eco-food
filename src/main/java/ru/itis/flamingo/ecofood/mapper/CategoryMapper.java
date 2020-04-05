package ru.itis.flamingo.ecofood.mapper;

import org.mapstruct.Mapper;
import ru.itis.flamingo.ecofood.domain.dto.CategoryDto;
import ru.itis.flamingo.ecofood.domain.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category mapToEntity(CategoryDto dto);

    CategoryDto mapToDto(Category entity);

}
