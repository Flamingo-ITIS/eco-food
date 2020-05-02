package ru.itis.flamingo.ecofood.util;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.flamingo.ecofood.domain.entity.Category;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.entity.enums.CategoryName;

import javax.persistence.criteria.Join;

public class ProductSpec {

    public static Specification<Product> categoryLike(CategoryName categoryName) {
        return (root, query, cb) -> {
            Join<Product, Category> productCategoryJoin = root.join("category");
            return cb.equal(productCategoryJoin.get("name"), categoryName);
        };
    }
}
