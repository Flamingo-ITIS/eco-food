package ru.itis.flamingo.ecofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flamingo.ecofood.domain.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Override
    Optional<Article> findById(Long aLong);

    @Override
    List<Article> findAll();
}
