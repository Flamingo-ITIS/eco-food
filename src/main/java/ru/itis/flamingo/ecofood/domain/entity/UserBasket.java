package ru.itis.flamingo.ecofood.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity(name = "basket_product")
@IdClass(Basket.class)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class UserBasket {

    @Id
    @ManyToOne
    @ToString.Exclude
    private User user;

    @Id
    @ManyToOne
    private Product product;

    private Integer count;

}
