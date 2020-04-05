package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;
import ru.itis.flamingo.ecofood.domain.dto.CategoryDto;
import ru.itis.flamingo.ecofood.domain.entity.enums.CategoryName;
import ru.itis.flamingo.ecofood.domain.repository.CategoryRepository;
import ru.itis.flamingo.ecofood.mapper.CategoryMapper;
import ru.itis.flamingo.ecofood.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto findByName(CategoryName categoryName) {
        return categoryRepository.findByName(categoryName)
            .map(categoryMapper::mapToDto)
            .orElseThrow(() -> new ResourceClosedException("Category with name " + categoryName + " not found"));
    }
}
