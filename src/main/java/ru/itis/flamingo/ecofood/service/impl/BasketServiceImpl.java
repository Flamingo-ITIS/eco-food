package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.flamingo.ecofood.domain.dto.ProductBasketDto;
import ru.itis.flamingo.ecofood.domain.entity.Basket;
import ru.itis.flamingo.ecofood.domain.entity.UserBasket;
import ru.itis.flamingo.ecofood.domain.repository.UserBasketRepository;
import ru.itis.flamingo.ecofood.mapper.ProductBasketMapper;
import ru.itis.flamingo.ecofood.mapper.ProductMapper;
import ru.itis.flamingo.ecofood.mapper.UserMapper;
import ru.itis.flamingo.ecofood.service.BasketService;
import ru.itis.flamingo.ecofood.service.ProductService;
import ru.itis.flamingo.ecofood.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final UserBasketRepository userBasketRepository;
    private final ProductBasketMapper productBasketMapper;

    @Override
    public void addProductToBasket(String username, Long productId, Integer count) {
        var user = userService.getUserByUsername(username);
        var product = productService.findById(productId);
        var basketItem = userBasketRepository.findById(new Basket()
            .setProduct(productId)
            .setUser(user.getId())
        );
        var item = new UserBasket();
        if (basketItem.isPresent()) {
            item = basketItem.get();
            item.setCount(count);
        } else {
            item.setCount(count)
                .setUser(userMapper.mapToEntity(user))
                .setProduct(productMapper.mapToEntity(product));
        }
        userBasketRepository.save(item);
    }

    @Override
    public List<ProductBasketDto> getProductFromBasket(String username) {
        return userService.getUserByUsername(username)
            .getBasket()
            .stream()
            .map(productBasketMapper::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteProductFromBasket(String username, Long productId) {
        var user = userService.getUserByUsername(username);
        var basketId = new Basket()
            .setProduct(productId)
            .setUser(user.getId());
        userBasketRepository.deleteById(basketId);
    }
}
