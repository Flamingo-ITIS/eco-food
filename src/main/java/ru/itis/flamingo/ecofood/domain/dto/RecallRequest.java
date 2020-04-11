package ru.itis.flamingo.ecofood.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RecallRequest {
    @NotNull
    private Long productId;
    private MultipartFile image;
    @NotNull
    @Min(1)
    @Max(5)
    private Short value;
    @NotNull
    private String message;
}
