package com.choimyeongheon.portfolio.domain.admin.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    private final String role;

    public static List<String> getNames() {
        return Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toList());
    }

}
