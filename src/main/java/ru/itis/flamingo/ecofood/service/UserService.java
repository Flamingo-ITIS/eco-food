package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.SignUpUserDto;
import ru.itis.flamingo.ecofood.domain.dto.UserDto;

public interface UserService {

    UserDto signUp(SignUpUserDto newUser);

    UserDto updateName(UserDto updatedUser);

    UserDto updatePhone(UserDto updatedUser);

    UserDto updateEmail(UserDto updatedUser);

    UserDto updatePassword(UserDto updatedUser);

}
