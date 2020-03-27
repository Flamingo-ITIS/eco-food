package ru.itis.flamingo.ecofood.service.impl;

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
        return Optional.of(userRepository.save(userMapper.mapToEntity(newUser).setRole(Role.PARTNER)))
            .map(userMapper::mapToDto)
            .get();
    }

    @Override
    public UserDto updateName(UserDto updatedUser) {
        updatedUser.setName(updatedUser.getName());
        return Optional.of(userRepository.save(userMapper.mapToEntity(updatedUser).setRole(Role.PARTNER)))
                .map(userMapper::mapToDto)
                .get();
    }

    @Override
    public UserDto updatePhone(UserDto updatedUser) {
        updatedUser.setContactPhone(updatedUser.getContactPhone());
        return Optional.of(userRepository.save(userMapper.mapToEntity(updatedUser).setRole(Role.PARTNER)))
                .map(userMapper::mapToDto)
                .get();
    }

    @Override
    public UserDto updateEmail(UserDto updatedUser) {
        updatedUser.setEmail(updatedUser.getEmail());
        return Optional.of(userRepository.save(userMapper.mapToEntity(updatedUser).setRole(Role.PARTNER)))
                .map(userMapper::mapToDto)
                .get();
    }

    @Override
    public UserDto updatePassword(UserDto updatedUser) {
        updatedUser.setPassword(updatedUser.getPassword());
        return Optional.of(userRepository.save(userMapper.mapToEntity(updatedUser).setRole(Role.PARTNER)))
                .map(userMapper::mapToDto)
                .get();
    }
}
