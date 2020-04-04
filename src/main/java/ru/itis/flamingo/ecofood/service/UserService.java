package ru.itis.flamingo.ecofood.service;

import ru.itis.flamingo.ecofood.domain.dto.SignUpUserDto;
import ru.itis.flamingo.ecofood.domain.dto.UserDto;

public interface UserService {

    UserDto signUp(SignUpUserDto newUser);

    UserDto getUserByUsername(String username);

    void update(UserDto userDto);

    void deleteUser(UserDto userDto);

}
