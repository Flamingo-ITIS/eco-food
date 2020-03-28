package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.ProductBasketDto;

import java.util.List;

public interface BasketService {

    void addProductToBasket(String username, Long productId, Integer count);

    List<ProductBasketDto> getProductFromBasket(String username);

    void deleteProductFromBasket(String username, Long productId);

}
