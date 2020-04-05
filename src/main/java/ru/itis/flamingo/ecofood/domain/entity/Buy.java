package ru.itis.flamingo.ecofood.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.enums.DeliveryType;
import ru.itis.flamingo.ecofood.domain.entity.enums.PaymentStatus;
import ru.itis.flamingo.ecofood.domain.entity.enums.PaymentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity(name = "buy")
@IdClass(BuyPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class Buy {

    @Id
    @ManyToOne
    @ToString.Exclude
    private User user;

    @Id
    @ManyToOne
    private Product product;

    @Column(name = "count")
    private Integer count;

    @Column(name = "cost")
    private Integer cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type")
    private DeliveryType deliveryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

}
