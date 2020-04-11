package ru.itis.flamingo.ecofood.mapper;


import org.mapstruct.Mapper;
import ru.itis.flamingo.ecofood.domain.dto.ArticleDto;
import ru.itis.flamingo.ecofood.domain.entity.Article;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDto mapToDto(Article entity);

    Article mapToEntity(ArticleDto dto);
}
