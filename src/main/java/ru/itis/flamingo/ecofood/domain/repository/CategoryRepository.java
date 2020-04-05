package ru.itis.flamingo.ecofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.flamingo.ecofood.domain.entity.Category;
import ru.itis.flamingo.ecofood.domain.entity.enums.CategoryName;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryName categoryName);

}
