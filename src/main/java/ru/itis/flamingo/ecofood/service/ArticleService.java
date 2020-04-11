package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.ArticleDto;
import ru.itis.flamingo.ecofood.domain.entity.Article;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> getAllArticles();
    ArticleDto getArticleById(Long id);
}
