package com.choimyeongheon.portfolio.global.security.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AdminAdapter extends User {

    private final Admin admin;

    public AdminAdapter(Admin admin, Collection<? extends GrantedAuthority> authorities) {
        super(admin.getUserId(), admin.getPassword(), authorities);
        this.admin = admin;
    }

}
