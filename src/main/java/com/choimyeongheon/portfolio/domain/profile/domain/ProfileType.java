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
    GROUP_EXHIBITIONS("단체전"),
    ETC("기타");

    private final String type;

    public static ProfileType of(String type) {
        for (ProfileType profileType : ProfileType.values()) {
            if (profileType.getType().equals(type)) {
                return profileType;
            }
        }
        return ProfileType.ETC;
    }

    public static List<String> getValues() {
        return Arrays.stream(ProfileType.values())
                .map(ProfileType::getType)
                .collect(Collectors.toList());
    }

}
