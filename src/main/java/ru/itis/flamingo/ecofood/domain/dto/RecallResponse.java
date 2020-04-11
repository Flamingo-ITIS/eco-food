package ru.itis.flamingo.ecofood.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RecallResponse {
    private Long id;
    private ProductDto product;
    private SimpleUserDto customer;
    private ImageDto image;
    private Short value;
    private String message;
}
