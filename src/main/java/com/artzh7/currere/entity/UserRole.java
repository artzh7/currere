package com.artzh7.currere.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
