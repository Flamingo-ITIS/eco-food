package ru.itis.flamingo.ecofood.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.flamingo.ecofood.domain.dto.ArticleDto;
import ru.itis.flamingo.ecofood.service.ArticleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
@Api(value = "ArticleController", description = "Articles operations")
@PreAuthorize("isAuthenticated()")
public class ArticleController {

    private final ArticleService articleService;

    @ApiOperation(
            value = "Get list of articles / Получить список статей"
    )
    @GetMapping
    public ResponseEntity<List<ArticleDto>> getAllArticles() {
        return new ResponseEntity<>(articleService.getAllArticles(), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get article by id / Получить статью по идентификатору"
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(articleService.getArticleById(id), HttpStatus.OK);
    }
}
