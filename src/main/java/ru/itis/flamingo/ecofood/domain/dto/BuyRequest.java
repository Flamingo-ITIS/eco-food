package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.enums.DeliveryType;
import ru.itis.flamingo.ecofood.domain.entity.enums.PaymentStatus;
import ru.itis.flamingo.ecofood.domain.entity.enums.PaymentType;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BuyRequest {
    private Long productId;
    private Integer count;
    private Double cost;
    private DeliveryType deliveryType;
    private PaymentType paymentType;
}
