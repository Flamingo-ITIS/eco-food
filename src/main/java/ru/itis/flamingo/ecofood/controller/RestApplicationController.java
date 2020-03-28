package ru.itis.flamingo.ecofood.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.flamingo.ecofood.domain.dto.SignUpUserDto;
import ru.itis.flamingo.ecofood.domain.dto.UserDto;
import ru.itis.flamingo.ecofood.service.UserService;

@RestController
@RequiredArgsConstructor
public class RestApplicationController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpUserDto signUpUserDto) {
        return new ResponseEntity<>(userService.signUp(signUpUserDto), HttpStatus.OK);
    }

    @PutMapping("/update-user")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
