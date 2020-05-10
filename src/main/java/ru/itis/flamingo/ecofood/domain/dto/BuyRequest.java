package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.enums.DeliveryType;
import ru.itis.flamingo.ecofood.domain.entity.enums.PaymentStatus;
import ru.itis.flamingo.ecofood.domain.entity.enums.PaymentType;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BuyRequest {
    @NotNull
    private Long productId;
    @NotNull
    private Integer count;
    @NotNull
    private Double cost;
    @NotNull
    private DeliveryType deliveryType;
    @NotNull
    private PaymentType paymentType;
}
