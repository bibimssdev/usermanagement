package com.springbank.user.core.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    READ,WRITE;

    @Override
    public String getAuthority() {
        return name();
    }
}
