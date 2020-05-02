package ru.itis.flamingo.ecofood.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.itis.flamingo.ecofood.domain.dto.ImageDto;
import ru.itis.flamingo.ecofood.domain.entity.Image;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-02T20:29:11+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class ImageDtoMapperImpl implements ImageDtoMapper {

    @Override
    public ImageDto apply(Image arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ImageDto imageDto = new ImageDto();

        imageDto.setId( arg0.getId() );
        imageDto.setName( arg0.getName() );

        return imageDto;
    }
}
