package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.dto.ProductRequest;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();

    ProductDto findById(Long id);

    ProductDto create(String username, ProductRequest productDto);

    void update(ProductDto productDto);

    void delete(ProductDto productDto);

    void delete(Long id);

    List<ProductDto> getProductsByUser(String username);

}
