package ru.itis.flamingo.ecofood.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.itis.flamingo.ecofood.domain.entity.Image;

@Data
@Accessors(chain = true)
public class ArticleDto {
    private Long id;
    private String name;
    private String lid;
    private String text;
    private String date;
}
