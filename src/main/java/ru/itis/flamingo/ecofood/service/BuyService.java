package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.BuyDto;
import ru.itis.flamingo.ecofood.domain.dto.BuyRequest;

import java.util.List;

public interface BuyService {

    BuyDto buyProduct(BuyRequest buyRequest, String username);

    List<BuyDto> getBuys(String username);

    void confirmBuy(String username, Long id);

}
