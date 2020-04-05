package ru.itis.flamingo.ecofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flamingo.ecofood.domain.entity.BuyPK;
import ru.itis.flamingo.ecofood.domain.entity.Buy;

public interface BuyRepository extends JpaRepository<Buy, BuyPK> {

}
