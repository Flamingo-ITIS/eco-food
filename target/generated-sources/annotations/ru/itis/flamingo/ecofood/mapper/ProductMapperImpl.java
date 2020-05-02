package ru.itis.flamingo.ecofood.mapper;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.flamingo.ecofood.domain.dto.CategoryDto;
import ru.itis.flamingo.ecofood.domain.dto.CommonSellerDto;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.entity.Category;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-02T20:29:11+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ProductDto mapToDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( entity.getId() );
        productDto.setTitle( entity.getTitle() );
        productDto.setDescription( entity.getDescription() );
        productDto.setRating( entity.getRating() );
        productDto.setCount( entity.getCount() );
        productDto.setCost( entity.getCost() );
        productDto.setCategory( categoryToCategoryDto( entity.getCategory() ) );
        productDto.setCountType( entity.getCountType() );
        productDto.setUser( userMapper.mapToCommonSellerDto( entity.getUser() ) );

        return productDto;
    }

    @Override
    public Product mapToEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( dto.getId() );
        product.setTitle( dto.getTitle() );
        product.setDescription( dto.getDescription() );
        product.setRating( dto.getRating() );
        product.setCountType( dto.getCountType() );
        product.setCount( dto.getCount() );
        product.setCost( dto.getCost() );
        product.setCategory( categoryDtoToCategory( dto.getCategory() ) );
        product.setUser( commonSellerDtoToUser( dto.getUser() ) );

        return product;
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
}
