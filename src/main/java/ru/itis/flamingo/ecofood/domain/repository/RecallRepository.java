package ru.itis.flamingo.ecofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.entity.Recall;

import java.util.List;

public interface RecallRepository extends JpaRepository<Recall, Long> {

    List<Recall> findAllByProduct(Product product);

}
