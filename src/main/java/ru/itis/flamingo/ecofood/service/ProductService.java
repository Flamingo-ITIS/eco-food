package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();

    ProductDto findById(Long id);

    ProductDto create(ProductDto productDto);

    void update(ProductDto productDto);

    void delete(ProductDto productDto);

    void delete(Long id);

}
