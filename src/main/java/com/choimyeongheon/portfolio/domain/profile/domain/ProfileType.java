package com.choimyeongheon.portfolio.domain.profile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum ProfileType {

    AWARDS("수상"),
    CAREER("약력"),
    SOLO_EXHIBITIONS("개인전"),
    GROUP_EXHIBITIONS("단체전");

    private final String value;

    public List<String> getValues() {
        return Arrays.stream(ProfileType.values())
                .map(profileType -> profileType.getValue())
                .collect(Collectors.toList());
    }

}
