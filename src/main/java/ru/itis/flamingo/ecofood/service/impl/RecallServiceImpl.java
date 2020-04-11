package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flamingo.ecofood.domain.dto.RecallRequest;
import ru.itis.flamingo.ecofood.domain.dto.RecallResponse;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.entity.Recall;
import ru.itis.flamingo.ecofood.domain.repository.RecallRepository;
import ru.itis.flamingo.ecofood.exception.ResourceNotFoundException;
import ru.itis.flamingo.ecofood.mapper.ProductMapper;
import ru.itis.flamingo.ecofood.mapper.RecallResponseMapper;
import ru.itis.flamingo.ecofood.mapper.UserMapper;
import ru.itis.flamingo.ecofood.service.MediaService;
import ru.itis.flamingo.ecofood.service.ProductService;
import ru.itis.flamingo.ecofood.service.RecallService;
import ru.itis.flamingo.ecofood.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecallServiceImpl implements RecallService {

    private final MediaService mediaService;
    private final UserService userService;
    private final ProductService productService;
    private final RecallRepository recallRepository;

    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final RecallResponseMapper recallResponseMapper;

    @Override
    @Transactional
    public void saveRecall(String username, RecallRequest recallRequest) {
        var image = recallRequest.getImage() != null
            ? mediaService.downloadImage(recallRequest.getImage())
            : null;
        var customer = userMapper.mapToEntity(userService.getUserByUsername(username));
        var product = productMapper.mapToEntity(productService.findById(recallRequest.getProductId()));
        var recall = new Recall()
            .setCustomer(customer)
            .setProduct(product)
            .setImage(image)
            .setMessage(recallRequest.getMessage())
            .setValue(recallRequest.getValue());
        recallRepository.save(recall);
        recalculateAvgRating(product);
        productService.update(productMapper.mapToDto(product));
    }

    @Override
    public List<RecallResponse> findAllByProduct(Long productId) {
        var product = productMapper.mapToEntity(productService.findById(productId));
        return recallRepository.findAllByProduct(product).stream()
            .map(recallResponseMapper)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteRecall(String username, Long recallId) {
        var user = userService.getUserByUsername(username);
        var recall = recallRepository.findById(recallId)
            .orElseThrow(() -> new ResourceNotFoundException("Recall with id = " + recallId + " not found"));
        if (!user.getId().equals(recall.getCustomer().getId())) {
            throw new IllegalArgumentException("Have not permission for delete this recall");
        }
        recallRepository.delete(recall);
    }

    private void recalculateAvgRating(Product product) {
        var allRecalls = this.findAllByProduct(product.getId());
        product.setRating(allRecalls.stream()
            .mapToDouble(RecallResponse::getValue)
            .sum() / allRecalls.size());
    }

}
