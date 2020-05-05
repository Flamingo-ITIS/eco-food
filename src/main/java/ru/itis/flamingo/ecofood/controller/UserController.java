package ru.itis.flamingo.ecofood.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flamingo.ecofood.domain.dto.SignUpUserDto;
import ru.itis.flamingo.ecofood.domain.dto.UserDto;
import ru.itis.flamingo.ecofood.service.MediaService;
import ru.itis.flamingo.ecofood.service.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MediaService mediaService;

    @ApiOperation(
        value = "Registration / Регистрация"
    )
    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpUserDto signUpUserDto) {
        return new ResponseEntity<>(userService.signUp(signUpUserDto), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Update user info / Обновить информацию  пользователя"
    )
    @PutMapping("/update-user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Delete user / Удалить пользователя"
    )
    @PutMapping("/delete-user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> delete(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Download user profile photo / Загрузить фото пользователя "
    )
    @PostMapping("/profile-photo")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateProfilePhoto(@RequestBody MultipartFile file,
                                             @AuthenticationPrincipal Principal principal) {
        mediaService.downloadProfilePhoto(principal.getName(), file);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(
        value = "Get authorization user profile photo / Получить фото пользователя, авторизованного на данный момент"
    )
    @GetMapping("/profile-photo")
    public ResponseEntity<byte[]> getProfilePhoto(@AuthenticationPrincipal Principal principal) {
        return new ResponseEntity<>(mediaService.getProfilePhoto(principal.getName()), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Get user profile photo by username / Получить фото пользователя по username"
    )
    @GetMapping("/{username}/profile-photo")
    public ResponseEntity<byte[]> getProfilePhoto(@PathVariable String username) {
        return new ResponseEntity<>(mediaService.getProfilePhoto(username), HttpStatus.OK);
    }

    @GetMapping("/{username}/users")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

}
