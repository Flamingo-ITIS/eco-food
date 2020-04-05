package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.dto.ProductRequest;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.repository.ProductRepository;
import ru.itis.flamingo.ecofood.mapper.CategoryMapper;
import ru.itis.flamingo.ecofood.mapper.ProductMapper;
import ru.itis.flamingo.ecofood.service.CategoryService;
import ru.itis.flamingo.ecofood.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
            .stream()
            .map(productMapper::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findById(id)
            .map(productMapper::mapToDto)
            .orElseThrow(() -> new IllegalArgumentException("Product with id = " + id + " not found"));
    }

    @Override
    public ProductDto create(ProductRequest productRequest) {
        var category = categoryService.findByName(productRequest.getCategory());
        var product = new Product()
            .setTitle(productRequest.getTitle())
            .setRating(0)
            .setDescription(productRequest.getDescription())
            .setCategory(categoryMapper.mapToEntity(category))
            .setCount(productRequest.getCount())
            .setCountType(productRequest.getCountType());
        product.setRating(0);
        return productMapper.mapToDto(productRepository.save(product));
    }

    @Override
    public void update(ProductDto productDto) {
        productRepository.save(productMapper.mapToEntity(productDto));
    }

    @Override
    public void delete(ProductDto productDto) {
        productRepository.delete(productMapper.mapToEntity(productDto));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
