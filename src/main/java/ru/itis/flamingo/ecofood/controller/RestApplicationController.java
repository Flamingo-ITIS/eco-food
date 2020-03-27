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

    @PutMapping("/update-username")
    public ResponseEntity<UserDto> updateUsername(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateName(userDto), HttpStatus.OK);
    }

    @PutMapping("/update-user-password")
    public ResponseEntity<UserDto> updateUserPassword(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updatePassword(userDto), HttpStatus.OK);
    }

    @PutMapping("/update-user-phone")
    public ResponseEntity<UserDto> updateUserPhone(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updatePhone(userDto), HttpStatus.OK);
    }

    @PutMapping("/update-user-email")
    public ResponseEntity<UserDto> updateUserEmail(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateEmail(userDto), HttpStatus.OK);
    }
}
