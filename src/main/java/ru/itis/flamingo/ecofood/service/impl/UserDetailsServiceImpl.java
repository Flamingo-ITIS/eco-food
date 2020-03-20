package ru.itis.flamingo.ecofood.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.flamingo.ecofood.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userResult = userRepository.findUserByUsername(username);
        if(userResult.isPresent()) {
            return userResult.get();
        } else {
            log.debug("User with username = {} not found", username);
            throw new UsernameNotFoundException("User with username = " + username + " not found");
        }
    }

}
