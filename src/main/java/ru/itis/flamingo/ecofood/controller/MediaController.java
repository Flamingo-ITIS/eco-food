package ru.itis.flamingo.ecofood.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.flamingo.ecofood.service.MediaService;

@RestController
@RequiredArgsConstructor
@Api(value = "MediaController", description = "Media operations")
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    @ApiOperation(
        value = "Get image / Получить фото"
    )
    @GetMapping("/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        return new ResponseEntity<>(mediaService.getMedia(imageName), HttpStatus.OK);
    }

}
