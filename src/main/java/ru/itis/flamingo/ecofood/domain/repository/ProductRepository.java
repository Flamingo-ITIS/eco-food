package ru.itis.flamingo.ecofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.flamingo.ecofood.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(name = "SELECT * FROM Product ORDER BY rating DESC LIMIT 10", nativeQuery = true)
    List<Product> getTop10ByOrderByRatingDesc();
}
