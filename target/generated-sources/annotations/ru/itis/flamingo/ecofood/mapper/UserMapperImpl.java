package ru.itis.flamingo.ecofood.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.itis.flamingo.ecofood.domain.dto.CategoryDto;
import ru.itis.flamingo.ecofood.domain.dto.CommonSellerDto;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.dto.SignUpUserDto;
import ru.itis.flamingo.ecofood.domain.dto.SimpleUserDto;
import ru.itis.flamingo.ecofood.domain.dto.UserDto;
import ru.itis.flamingo.ecofood.domain.entity.Buy;
import ru.itis.flamingo.ecofood.domain.entity.Category;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.entity.User;
import ru.itis.flamingo.ecofood.domain.entity.enums.Role;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-02T20:29:11+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

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
        Set<Buy> set = entity.getBuys();
        if ( set != null ) {
            userDto.setBuys( new ArrayList<Buy>( set ) );
        }
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
        List<Buy> list2 = dto.getBuys();
        if ( list2 != null ) {
            user.setBuys( new HashSet<Buy>( list2 ) );
        }

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
}
