package ru.itis.flamingo.ecofood.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.flamingo.ecofood.domain.dto.ProductBasketDto;
import ru.itis.flamingo.ecofood.service.BasketService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "BasketController", description = "Basket operations")
@RequestMapping("/basket")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BasketController {

    private final BasketService basketService;

    @ApiOperation(
        value = "Add product to basket / Добавить продукт в корзину"
    )
    @PostMapping("/{productId}")
    public ResponseEntity addProductToBasket(@PathVariable Long productId,
                                             @RequestParam Integer count,
                                             @AuthenticationPrincipal Principal principal) {
        basketService.addProductToBasket(principal.getName(), productId, count);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Get products from basket / Получить продукты из корзины"
    )
    @GetMapping
    public ResponseEntity<List<ProductBasketDto>> getBasketList(@AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(basketService.getProductFromBasket(principal.getName()), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Delete product from basket / Удалить продукты из корзины"
    )
    @DeleteMapping("/{productId}")
    public ResponseEntity deleteFromBasket(@PathVariable Long productId,
                                           @AuthenticationPrincipal Principal principal) {
        basketService.deleteProductFromBasket(principal.getName(), productId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
