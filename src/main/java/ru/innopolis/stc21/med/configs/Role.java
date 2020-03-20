package ru.innopolis.stc21.med.configs;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    DOCTOR,
    PACIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
