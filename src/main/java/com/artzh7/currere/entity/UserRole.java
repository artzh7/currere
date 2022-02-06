package com.artzh7.currere.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER("Пользователь"),
    ADMIN("Админ"),
    RESTAURANT("Ресторан"),
    COURIER("Курьер");

    @Getter
    private final String title;

    UserRole(String title) {
        this.title = title;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
