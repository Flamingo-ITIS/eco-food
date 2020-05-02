package ru.itis.flamingo.ecofood.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.itis.flamingo.ecofood.domain.dto.BuyDto;
import ru.itis.flamingo.ecofood.domain.entity.Buy;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-02T20:29:11+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class BuyMapperImpl implements BuyMapper {

    @Override
    public BuyDto apply(Buy buy) {
        if ( buy == null ) {
            return null;
        }

        BuyDto buyDto = new BuyDto();

        buyDto.setProductId( buyProductId( buy ) );
        buyDto.setTitle( buyProductTitle( buy ) );
        buyDto.setUserId( buyUserId( buy ) );
        buyDto.setId( buy.getId() );
        buyDto.setCount( buy.getCount() );
        buyDto.setCost( buy.getCost() );
        buyDto.setDeliveryType( buy.getDeliveryType() );
        buyDto.setPaymentType( buy.getPaymentType() );
        buyDto.setStatus( buy.getStatus() );

        return buyDto;
    }

    private Long buyProductId(Buy buy) {
        if ( buy == null ) {
            return null;
        }
        Product product = buy.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String buyProductTitle(Buy buy) {
        if ( buy == null ) {
            return null;
        }
        Product product = buy.getProduct();
        if ( product == null ) {
            return null;
        }
        String title = product.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private Long buyUserId(Buy buy) {
        if ( buy == null ) {
            return null;
        }
        User user = buy.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
