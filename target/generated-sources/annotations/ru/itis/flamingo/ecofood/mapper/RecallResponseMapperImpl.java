package ru.itis.flamingo.ecofood.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.itis.flamingo.ecofood.domain.dto.CategoryDto;
import ru.itis.flamingo.ecofood.domain.dto.CommonSellerDto;
import ru.itis.flamingo.ecofood.domain.dto.ImageDto;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.dto.RecallResponse;
import ru.itis.flamingo.ecofood.domain.dto.SimpleUserDto;
import ru.itis.flamingo.ecofood.domain.entity.Category;
import ru.itis.flamingo.ecofood.domain.entity.Image;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.entity.Recall;
import ru.itis.flamingo.ecofood.domain.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-13T00:29:20+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class RecallResponseMapperImpl implements RecallResponseMapper {

    @Override
    public RecallResponse apply(Recall arg0) {
        if ( arg0 == null ) {
            return null;
        }

        RecallResponse recallResponse = new RecallResponse();

        recallResponse.setId( arg0.getId() );
        recallResponse.setProduct( productToProductDto( arg0.getProduct() ) );
        recallResponse.setCustomer( userToSimpleUserDto( arg0.getCustomer() ) );
        recallResponse.setImage( imageToImageDto( arg0.getImage() ) );
        recallResponse.setValue( arg0.getValue() );
        recallResponse.setMessage( arg0.getMessage() );

        return recallResponse;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );

        return categoryDto;
    }

    protected CommonSellerDto userToCommonSellerDto(User user) {
        if ( user == null ) {
            return null;
        }

        CommonSellerDto commonSellerDto = new CommonSellerDto();

        commonSellerDto.setId( user.getId() );
        commonSellerDto.setUsername( user.getUsername() );
        commonSellerDto.setName( user.getName() );
        if ( user.getRating() != null ) {
            commonSellerDto.setRating( user.getRating().longValue() );
        }
        commonSellerDto.setContactPhone( user.getContactPhone() );
        commonSellerDto.setGeoPosition( user.getGeoPosition() );
        commonSellerDto.setEmail( user.getEmail() );

        return commonSellerDto;
    }

    protected ImageDto imageToImageDto(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageDto imageDto = new ImageDto();

        imageDto.setId( image.getId() );
        imageDto.setName( image.getName() );

        return imageDto;
    }

    protected List<ImageDto> imageListToImageDtoList(List<Image> list) {
        if ( list == null ) {
            return null;
        }

        List<ImageDto> list1 = new ArrayList<ImageDto>( list.size() );
        for ( Image image : list ) {
            list1.add( imageToImageDto( image ) );
        }

        return list1;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setTitle( product.getTitle() );
        productDto.setDescription( product.getDescription() );
        productDto.setRating( product.getRating() );
        productDto.setCount( product.getCount() );
        productDto.setCost( product.getCost() );
        productDto.setCategory( categoryToCategoryDto( product.getCategory() ) );
        productDto.setCountType( product.getCountType() );
        productDto.setUser( userToCommonSellerDto( product.getUser() ) );
        productDto.setImages( imageListToImageDtoList( product.getImages() ) );

        return productDto;
    }

    protected SimpleUserDto userToSimpleUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        SimpleUserDto simpleUserDto = new SimpleUserDto();

        simpleUserDto.setId( user.getId() );
        simpleUserDto.setUsername( user.getUsername() );
        simpleUserDto.setName( user.getName() );

        return simpleUserDto;
    }
}
