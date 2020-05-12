package ru.itis.flamingo.ecofood.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.itis.flamingo.ecofood.domain.dto.ArticleDto;
import ru.itis.flamingo.ecofood.domain.entity.Article;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-13T00:30:47+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public ArticleDto mapToDto(Article entity) {
        if ( entity == null ) {
            return null;
        }

        ArticleDto articleDto = new ArticleDto();

        articleDto.setId( entity.getId() );
        articleDto.setName( entity.getName() );
        articleDto.setLid( entity.getLid() );
        articleDto.setText( entity.getText() );
        articleDto.setDate( entity.getDate() );

        return articleDto;
    }

    @Override
    public Article mapToEntity(ArticleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Article article = new Article();

        article.setId( dto.getId() );
        article.setName( dto.getName() );
        article.setLid( dto.getLid() );
        article.setText( dto.getText() );
        article.setDate( dto.getDate() );

        return article;
    }
}
