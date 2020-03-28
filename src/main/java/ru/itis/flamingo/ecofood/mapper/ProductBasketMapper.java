package ru.itis.flamingo.ecofood.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.flamingo.ecofood.domain.dto.ProductBasketDto;
import ru.itis.flamingo.ecofood.domain.entity.UserBasket;

@Mapper(componentModel = "spring")
public interface ProductBasketMapper {

    @Mapping(target = "title", source = "product.title")
    @Mapping(target = "id", source = "product.id")
    ProductBasketDto mapToDto(UserBasket userBasket);
}
