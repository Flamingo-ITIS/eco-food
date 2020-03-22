package ru.itis.flamingo.ecofood.mapper;

import org.mapstruct.Mapper;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto mapToDto(Product entity);

    Product mapToEntity(ProductDto dto);

}
