package ru.itis.flamingo.ecofood.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.flamingo.ecofood.domain.dto.BuyDto;
import ru.itis.flamingo.ecofood.domain.entity.Buy;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface BuyMapper extends Function<Buy, BuyDto> {

    @Mapping(target = "title", source = "product.title")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "userId", source = "user.id")
    @Override
    BuyDto apply(Buy buy);
}
