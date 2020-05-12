package ru.itis.flamingo.ecofood.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.flamingo.ecofood.domain.dto.CategoryDto;
import ru.itis.flamingo.ecofood.domain.dto.CommonSellerDto;
import ru.itis.flamingo.ecofood.domain.dto.ImageDto;
import ru.itis.flamingo.ecofood.domain.dto.ProductDto;
import ru.itis.flamingo.ecofood.domain.entity.Category;
import ru.itis.flamingo.ecofood.domain.entity.Image;
import ru.itis.flamingo.ecofood.domain.entity.Product;
import ru.itis.flamingo.ecofood.domain.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-13T00:29:20+0300",
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
        productDto.setImages( imageListToImageDtoList( entity.getImages() ) );

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
        product.setImages( imageDtoListToImageList( dto.getImages() ) );

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
}
