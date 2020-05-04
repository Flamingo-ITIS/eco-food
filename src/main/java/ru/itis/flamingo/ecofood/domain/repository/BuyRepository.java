package ru.itis.flamingo.ecofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flamingo.ecofood.domain.entity.Buy;

import java.util.Optional;

public interface BuyRepository extends JpaRepository<Buy, Long> {

    Optional<Buy> getBuyById(Long id);
}
