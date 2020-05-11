package ru.itis.flamingo.ecofood.service.impl;

import liquibase.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.flamingo.ecofood.domain.dto.SignUpUserDto;
import ru.itis.flamingo.ecofood.domain.dto.UserDto;
import ru.itis.flamingo.ecofood.domain.entity.enums.Role;
import ru.itis.flamingo.ecofood.domain.repository.UserRepository;
import ru.itis.flamingo.ecofood.mapper.UserMapper;
import ru.itis.flamingo.ecofood.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto signUp(SignUpUserDto newUser) {
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setIsDeleted(false);
        return Optional.of(userRepository.save(userMapper.mapToEntity(newUser).setRole(Role.PARTNER)))
                .map(userMapper::mapToDto)
                .get();
    }

    @Override
    public void update(UserDto updatedUser) {
        userRepository.save(userMapper.mapToEntity(updatedUser));
    }

    @Override
    public void deleteUser(UserDto userDto){
        userDto.setIsDeleted(true);
        userRepository.save(userMapper.mapToEntity(userDto));
    }

    @Override
    public UserDto getUserByUserId(Long id) {
        return userRepository.findUserById(id)
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .map(userMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("User with username " + username + " not found"));
    }

}
