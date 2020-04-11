package ru.itis.flamingo.ecofood.mapper;

import org.mapstruct.Mapper;
import ru.itis.flamingo.ecofood.domain.dto.ImageDto;
import ru.itis.flamingo.ecofood.domain.entity.Image;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface ImageDtoMapper extends Function<Image, ImageDto> {
}
