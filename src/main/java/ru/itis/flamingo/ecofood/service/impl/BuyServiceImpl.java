package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.flamingo.ecofood.domain.dto.BuyDto;
import ru.itis.flamingo.ecofood.domain.dto.BuyRequest;
import ru.itis.flamingo.ecofood.domain.entity.Buy;
import ru.itis.flamingo.ecofood.domain.entity.enums.PaymentStatus;
import ru.itis.flamingo.ecofood.domain.repository.BuyRepository;
import ru.itis.flamingo.ecofood.mapper.BuyMapper;
import ru.itis.flamingo.ecofood.mapper.ProductMapper;
import ru.itis.flamingo.ecofood.mapper.UserMapper;
import ru.itis.flamingo.ecofood.service.BuyService;
import ru.itis.flamingo.ecofood.service.ProductService;
import ru.itis.flamingo.ecofood.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl implements BuyService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final BuyRepository buyRepository;
    private final BuyMapper buyMapper;


    @Override
    public BuyDto buyProduct(BuyRequest buyRequest, String username) {
        var user = userService.getUserByUsername(username);
        var product = productService.findById(buyRequest.getId());
        var buy = new Buy()
            .setCount(buyRequest.getCount())
            .setProduct(productMapper.mapToEntity(product))
            .setUser(userMapper.mapToEntity(user))
            .setCost(buyRequest.getCost())
            .setDeliveryType(buyRequest.getDeliveryType())
            .setPaymentType(buyRequest.getPaymentType())
            .setStatus(PaymentStatus.PAID);
        return buyMapper.mapToDto(buyRepository.save(buy));
    }

    @Override
    public List<BuyDto> getBuys(String username) {
        return userService.getUserByUsername(username)
            .getBuys()
            .stream()
            .map(buyMapper::mapToDto)
            .collect(Collectors.toList());
    }
}
