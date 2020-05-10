package ru.itis.flamingo.ecofood.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.dto.ProductRequest;
import ru.itis.flamingo.ecofood.domain.filter.ProductFilter;
import ru.itis.flamingo.ecofood.service.MediaService;
import ru.itis.flamingo.ecofood.service.ProductService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "ProductController", description = "Product operations")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final MediaService mediaService;

    @ApiOperation(
        value = "Get list of products / Получить список продуктов"
    )
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(ProductFilter filter, Pageable pageable) {
        return new ResponseEntity<>(productService.findAll(filter, pageable), HttpStatus.OK);
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProductDto> save(@RequestBody @Validated ProductRequest productRequest,
                                           @AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(productService.create(principal.getName(), productRequest), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Update product / Обновить товар"
    )
    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity update(@RequestBody ProductDto productDto) {
        productService.update(productDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Delete product / Удалить товар"
    )
    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity delete(@RequestBody ProductDto productDto) {
        productService.delete(productDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Delete product by id / Удалить товар по идентификатору"
    )
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Get user's products / Получить товары пользователя по username"
    )
    @GetMapping("/{username}/users")
    public ResponseEntity<List<ProductDto>> getProductsByUser(@PathVariable String username) {
        return new ResponseEntity<>(productService.getProductsByUser(username), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get top products / Получить список продуктов с наивысшей оценкой"
    )
    @GetMapping("/top")
    public ResponseEntity<List<ProductDto>> getTopProducts() {
        return new ResponseEntity<>(productService.getTopProducts(), HttpStatus.OK);
    }


    @ApiOperation(
            value = "Upload product images / Загрузить фотографии продукта"
    )
    @PostMapping("/{productId}/images")
    public ResponseEntity<ProductDto> uploadImages(@PathVariable Long productId,
                                                   @RequestBody List<MultipartFile> fileList) {
        return new ResponseEntity<>(mediaService.uploadProductImages(productId, fileList), HttpStatus.OK);
    }

}
