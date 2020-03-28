package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.service.FavoritesService;
import ru.itis.flamingo.ecofood.service.ProductService;
import ru.itis.flamingo.ecofood.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoritesServiceImpl implements FavoritesService {

    private final UserService userService;
    private final ProductService productService;

    @Override
    public void addToFavorites(String username, Long productId) {
        var user = userService.getUserByUsername(username);
        var product = productService.findById(productId);
        user.getFavorites().add(product);
        userService.update(user);
    }

    @Override
    public boolean deleteFromFavorites(String username, Long productId) {
        var user = userService.getUserByUsername(username);
        var product = productService.findById(productId);
        var result = user.getFavorites().remove(product);
        if (result) {
            userService.update(user);
        } else {
            log.debug("User's favourites not contains product with id = {}", productId);
        }
        return result;
    }

    @Override
    public List<ProductDto> getFavorites(String username) {
        return userService.getUserByUsername(username).getFavorites();
    }
}
