package ru.itis.flamingo.ecofood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flamingo.ecofood.domain.entity.Basket;
import ru.itis.flamingo.ecofood.domain.entity.UserBasket;

public interface UserBasketRepository extends JpaRepository<UserBasket, Basket> {

}
