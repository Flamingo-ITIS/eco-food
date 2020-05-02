package ru.itis.flamingo.ecofood.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.flamingo.ecofood.domain.dto.BuyDto;
import ru.itis.flamingo.ecofood.domain.dto.BuyRequest;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.service.BuyService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "Buy Controller", description = "Buy operations")
@RequestMapping("/buys")
@PreAuthorize("isAuthenticated()")
public class BuyController {

    private final BuyService buyService;

    @ApiOperation(
        value = "Buy product / Купить продукт"
    )
    @PostMapping
    public ResponseEntity buyProduct(@RequestBody BuyRequest buyRequest,
                                     @AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(buyService.buyProduct(buyRequest, principal.getName()), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Get products from buys / Получить все совершенные покупки"
    )
    @GetMapping
    public ResponseEntity<List<BuyDto>> getBuys(@AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(buyService.getBuys(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<Void> confirmOrder(@AuthenticationPrincipal Principal principal, @PathVariable Long id) {
        buyService.confirmBuy(principal.getName(), id);
        return ResponseEntity.ok().build();
    }

}
