package ru.itis.flamingo.ecofood.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.dto.ProductRequest;
import ru.itis.flamingo.ecofood.service.ProductService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "ProductController", description = "Product operations")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @ApiOperation(
        value = "Get list of products / Получить список продуктов"
    )
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Get product by id / Получить товар по идентификатору"
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Create new product / Создать новый товар"
    )
    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody @Validated ProductRequest productRequest,
                                           @AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(productService.create(principal.getName(), productRequest), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Update product / Обновить товар"
    )
    @PutMapping
    public ResponseEntity update(@RequestBody ProductDto productDto) {
        productService.update(productDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Delete product / Удалить товар"
    )
    @DeleteMapping
    public ResponseEntity delete(@RequestBody ProductDto productDto) {
        productService.delete(productDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Delete product by id / Удалить товар по идентификатору"
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Get user's products / Получить товары пользователя"
    )
    @GetMapping("/{username}/users")
    public ResponseEntity<List<ProductDto>> getProductsByUser(@PathVariable String username) {
        return new ResponseEntity<>(productService.getProductsByUser(username), HttpStatus.OK);
    }


}
