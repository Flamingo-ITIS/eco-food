package ru.itis.flamingo.ecofood.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.flamingo.ecofood.domain.dto.BuyDto;
import ru.itis.flamingo.ecofood.domain.dto.CategoryDto;
import ru.itis.flamingo.ecofood.domain.dto.CommonSellerDto;
import ru.itis.flamingo.ecofood.domain.dto.ImageDto;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.dto.SignUpUserDto;
import ru.itis.flamingo.ecofood.domain.dto.SimpleUserDto;
import ru.itis.flamingo.ecofood.domain.dto.UserDto;
import ru.itis.flamingo.ecofood.domain.entity.Buy;
import ru.itis.flamingo.ecofood.domain.entity.Category;
import ru.itis.flamingo.ecofood.domain.entity.Image;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.entity.User;
import ru.itis.flamingo.ecofood.domain.entity.enums.Role;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-13T00:30:47+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private BuyMapper buyMapper;

    @Override
    public UserDto mapToDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( entity.getId() );
        userDto.setUsername( entity.getUsername() );
        userDto.setName( entity.getName() );
        if ( entity.getRole() != null ) {
            userDto.setRole( entity.getRole().name() );
        }
        if ( entity.getRating() != null ) {
            userDto.setRating( entity.getRating().longValue() );
        }
        userDto.setContactPhone( entity.getContactPhone() );
        userDto.setGeoPosition( entity.getGeoPosition() );
        userDto.setEmail( entity.getEmail() );
        userDto.setFavorites( productListToProductDtoList( entity.getFavorites() ) );
        userDto.setBuys( buySetToBuyDtoList( entity.getBuys() ) );
        userDto.setIsDeleted( entity.getIsDeleted() );
        userDto.setProducts( productListToProductDtoList( entity.getProducts() ) );

        return userDto;
    }

    @Override
    public User mapToEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setName( dto.getName() );
        user.setUsername( dto.getUsername() );
        if ( dto.getRating() != null ) {
            user.setRating( dto.getRating().intValue() );
        }
        user.setContactPhone( dto.getContactPhone() );
        user.setEmail( dto.getEmail() );
        user.setGeoPosition( dto.getGeoPosition() );
        user.setIsDeleted( dto.getIsDeleted() );
        if ( dto.getRole() != null ) {
            user.setRole( Enum.valueOf( Role.class, dto.getRole() ) );
        }
        user.setFavorites( productDtoListToProductList( dto.getFavorites() ) );
        user.setProducts( productDtoListToProductList( dto.getProducts() ) );
        user.setBuys( buyDtoListToBuySet( dto.getBuys() ) );

        return user;
    }

    @Override
    public User mapToEntity(SignUpUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setName( dto.getName() );
        user.setUsername( dto.getUsername() );
        user.setPassword( dto.getPassword() );
        user.setContactPhone( dto.getContactPhone() );
        user.setGeoPosition( dto.getGeoPosition() );
        user.setIsDeleted( dto.getIsDeleted() );

        return user;
    }

    @Override
    public CommonSellerDto mapToCommonSellerDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        CommonSellerDto commonSellerDto = new CommonSellerDto();

        commonSellerDto.setId( entity.getId() );
        commonSellerDto.setUsername( entity.getUsername() );
        commonSellerDto.setName( entity.getName() );
        if ( entity.getRating() != null ) {
            commonSellerDto.setRating( entity.getRating().longValue() );
        }
        commonSellerDto.setContactPhone( entity.getContactPhone() );
        commonSellerDto.setGeoPosition( entity.getGeoPosition() );
        commonSellerDto.setEmail( entity.getEmail() );

        return commonSellerDto;
    }

    @Override
    public SimpleUserDto mapToSimpleUser(User user) {
        if ( user == null ) {
            return null;
        }

        SimpleUserDto simpleUserDto = new SimpleUserDto();

        simpleUserDto.setId( user.getId() );
        simpleUserDto.setUsername( user.getUsername() );
        simpleUserDto.setName( user.getName() );

        return simpleUserDto;
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
        productDto.setUser( mapToCommonSellerDto( product.getUser() ) );
        productDto.setImages( imageListToImageDtoList( product.getImages() ) );

        return productDto;
    }

    protected List<ProductDto> productListToProductDtoList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDto> list1 = new ArrayList<ProductDto>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDto( product ) );
        }

        return list1;
    }

    protected List<BuyDto> buySetToBuyDtoList(Set<Buy> set) {
        if ( set == null ) {
            return null;
        }

        List<BuyDto> list = new ArrayList<BuyDto>( set.size() );
        for ( Buy buy : set ) {
            list.add( buyMapper.apply( buy ) );
        }

        return list;
    }

    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setName( categoryDto.getName() );

        return category;
    }

    protected User commonSellerDtoToUser(CommonSellerDto commonSellerDto) {
        if ( commonSellerDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( commonSellerDto.getId() );
        user.setName( commonSellerDto.getName() );
        user.setUsername( commonSellerDto.getUsername() );
        if ( commonSellerDto.getRating() != null ) {
            user.setRating( commonSellerDto.getRating().intValue() );
        }
        user.setContactPhone( commonSellerDto.getContactPhone() );
        user.setEmail( commonSellerDto.getEmail() );
        user.setGeoPosition( commonSellerDto.getGeoPosition() );

        return user;
    }

    protected Image imageDtoToImage(ImageDto imageDto) {
        if ( imageDto == null ) {
            return null;
        }

        Image image = new Image();

        image.setId( imageDto.getId() );
        image.setName( imageDto.getName() );

        return image;
    }

    protected List<Image> imageDtoListToImageList(List<ImageDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Image> list1 = new ArrayList<Image>( list.size() );
        for ( ImageDto imageDto : list ) {
            list1.add( imageDtoToImage( imageDto ) );
        }

        return list1;
    }

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDto.getId() );
        product.setTitle( productDto.getTitle() );
        product.setDescription( productDto.getDescription() );
        product.setRating( productDto.getRating() );
        product.setCountType( productDto.getCountType() );
        product.setCount( productDto.getCount() );
        product.setCost( productDto.getCost() );
        product.setCategory( categoryDtoToCategory( productDto.getCategory() ) );
        product.setUser( commonSellerDtoToUser( productDto.getUser() ) );
        product.setImages( imageDtoListToImageList( productDto.getImages() ) );

        return product;
    }

    protected List<Product> productDtoListToProductList(List<ProductDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDto productDto : list ) {
            list1.add( productDtoToProduct( productDto ) );
        }

        return list1;
    }

    protected Buy buyDtoToBuy(BuyDto buyDto) {
        if ( buyDto == null ) {
            return null;
        }

        Buy buy = new Buy();

        buy.setId( buyDto.getId() );
        buy.setCount( buyDto.getCount() );
        buy.setCost( buyDto.getCost() );
        buy.setDeliveryType( buyDto.getDeliveryType() );
        buy.setPaymentType( buyDto.getPaymentType() );
        buy.setStatus( buyDto.getStatus() );

        return buy;
    }

    protected Set<Buy> buyDtoListToBuySet(List<BuyDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Buy> set = new HashSet<Buy>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( BuyDto buyDto : list ) {
            set.add( buyDtoToBuy( buyDto ) );
        }

        return set;
    }
}
