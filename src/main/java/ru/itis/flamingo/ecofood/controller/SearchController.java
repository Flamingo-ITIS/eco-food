package ru.itis.flamingo.ecofood.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.service.SearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Api(value = "SearchController")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> productSearch(@RequestParam("template") String template) {
        return ResponseEntity.ok(searchService.searchProductsByTemplate(template));
    }
}
