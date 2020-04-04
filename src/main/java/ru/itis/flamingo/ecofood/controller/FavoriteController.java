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
import org.springframework.web.bind.annotation.RestController;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.service.FavoritesService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "FavoriteController", description = "Favorite operations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FavoriteController {

    private final FavoritesService favoritesService;

    @ApiOperation(
        value = "Add to favorites / Добавить в избранное"
    )
    @PostMapping(value = "/{productId}/favorites")
    public ResponseEntity addToFavorites(@PathVariable Long productId,
                                         @AuthenticationPrincipal Principal principal) {
        favoritesService.addToFavorites(principal.getName(), productId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Delete from favorite / Удалить из избарнного"
    )
    @DeleteMapping(value = "/{productId}/favorites")
    public ResponseEntity deleteFromFavorites(@PathVariable Long productId,
                                             @AuthenticationPrincipal Principal principal) {
        return favoritesService.deleteFromFavorites(principal.getName(), productId)
            ? new ResponseEntity(HttpStatus.OK)
            : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/favorites")
    public ResponseEntity<List<ProductDto>> getFavorites(@AuthenticationPrincipal Principal principal) {
        var favorites = favoritesService.getFavorites(principal.getName());
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }
}
