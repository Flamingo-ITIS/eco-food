package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.ProductDto;

import java.util.List;

public interface FavoritesService {

    void addToFavorites(String username, Long productId);

    boolean deleteFromFavorites(String username, Long productId);

    List<ProductDto> getFavorites(String username);

}
