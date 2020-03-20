package ru.itis.flamingo.ecofood.domain.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    PARTNER;

    @Override
    public String getAuthority() {
        return name();
    }
}
