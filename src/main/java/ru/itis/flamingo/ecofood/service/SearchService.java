package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.ProductDto;

import java.util.List;

public interface SearchService {

    List<ProductDto> searchProductsByTemplate(String template);

}
