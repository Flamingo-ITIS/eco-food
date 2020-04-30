package ru.itis.flamingo.ecofood.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flamingo.ecofood.domain.entity.enums.DeliveryType;
import ru.itis.flamingo.ecofood.domain.entity.enums.PaymentStatus;
import ru.itis.flamingo.ecofood.domain.entity.enums.PaymentType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDto {
    private String title;
    private Long id;
    private Long productId;
    private Long userId;
    private Integer count;
    private Double cost;
    private DeliveryType deliveryType;
    private PaymentType paymentType;
    private PaymentStatus status;
}
