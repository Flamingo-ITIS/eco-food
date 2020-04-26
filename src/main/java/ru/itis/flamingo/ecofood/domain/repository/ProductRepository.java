package ru.itis.flamingo.ecofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.flamingo.ecofood.domain.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(name = "SELECT * FROM Product ORDER BY rating DESC LIMIT 10", nativeQuery = true)
    List<Product> getTop10ByOrderByRatingDesc();

    @Query("FROM Product p WHERE p.title LIKE :template OR p.description LIKE :template")
    List<Product> searchProducts(@Param("template") String template);
}
