package com.choimyeongheon.portfolio.domain.profile.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum ProfileType {

    AWARDS("수상", "awards"),
    CAREER("약력", "career"),
    SOLO_EXHIBITIONS("개인전", "solo-exhibitions"),
    GROUP_EXHIBITIONS("단체전", "group-exhibitions"),
    ETC("기타", "etc");

    private final String type;
    private final String lowerCase;

    public static ProfileType from(String lowerCase) {
        for (ProfileType profileType : ProfileType.values()) {
            if (profileType.getLowerCase().equals(lowerCase)) {
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
