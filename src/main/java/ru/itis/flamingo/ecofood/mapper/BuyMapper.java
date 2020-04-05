package ru.itis.flamingo.ecofood.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.flamingo.ecofood.domain.dto.BuyDto;
import ru.itis.flamingo.ecofood.domain.entity.Buy;

@Mapper(componentModel = "spring")
public interface BuyMapper {

    @Mapping(target = "title", source = "product.title")
    @Mapping(target = "id", source = "product.id")
    BuyDto mapToDto(Buy buy);
}
