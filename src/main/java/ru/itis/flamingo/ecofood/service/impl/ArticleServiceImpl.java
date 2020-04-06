package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.flamingo.ecofood.domain.dto.ArticleDto;
import ru.itis.flamingo.ecofood.domain.entity.Article;
import ru.itis.flamingo.ecofood.domain.repository.ArticleRepository;
import ru.itis.flamingo.ecofood.mapper.ArticleMapper;
import ru.itis.flamingo.ecofood.service.ArticleService;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public List<ArticleDto> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDto> articlesDto = new ArrayList<>();
        for (Article article : articles) {
            articlesDto.add(articleMapper.mapToDto(article));
        }
        return articlesDto;
    }

    @Override
    public ArticleDto getArticleById(Long id){
        return articleRepository.findById(id)
                .map(articleMapper :: mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Article with id " + id + " not found"));
    }
}
