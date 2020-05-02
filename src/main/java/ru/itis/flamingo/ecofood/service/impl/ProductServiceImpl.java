package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.dto.ProductRequest;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.filter.ProductFilter;
import ru.itis.flamingo.ecofood.domain.repository.ProductRepository;
import ru.itis.flamingo.ecofood.mapper.CategoryMapper;
import ru.itis.flamingo.ecofood.mapper.ProductMapper;
import ru.itis.flamingo.ecofood.mapper.UserMapper;
import ru.itis.flamingo.ecofood.service.CategoryService;
import ru.itis.flamingo.ecofood.service.ProductService;
import ru.itis.flamingo.ecofood.service.UserService;
import ru.itis.flamingo.ecofood.util.FilterProcessor;
import ru.itis.flamingo.ecofood.util.ProductSpec;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    private final FilterProcessor<ProductFilter, Product> filterProcessor = FilterProcessor
        .strict(ProductFilter::getCategory, ProductSpec::categoryLike)
        .build();

    @Override
    public List<ProductDto> findAll(ProductFilter filter, Pageable pageable) {
        return productRepository.findAll(filterProcessor.buildSpec(filter))
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
    public ProductDto create(String username, ProductRequest productRequest) {
        var category = categoryService.findByName(productRequest.getCategory());
        var user = userService.getUserByUsername(username);
        var product = new Product()
                .setTitle(productRequest.getTitle())
                .setRating(0D)
                .setDescription(productRequest.getDescription())
                .setCategory(categoryMapper.mapToEntity(category))
                .setCount(productRequest.getCount())
                .setCountType(productRequest.getCountType())
                .setCost(productRequest.getCost())
                .setUser(userMapper.mapToEntity(user));
        product.setRating(0D);
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

    @Override
    public List<ProductDto> getProductsByUser(String username) {
        var user = userService.getUserByUsername(username);
        return user.getProducts();
    }

    @Override
    public List<ProductDto> getProductsByUserId(Long id) {
        var user = userService.getUserByUserId(id);
        return user.getProducts();
    }

    @Override
    public List<ProductDto> getTopProducts() {
        return productRepository.getTop10ByOrderByRatingDesc()
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
