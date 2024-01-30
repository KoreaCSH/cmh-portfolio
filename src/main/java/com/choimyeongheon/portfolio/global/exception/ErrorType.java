package com.choimyeongheon.portfolio.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    EMPTY_DELETION_LIST("삭제할 이미지를 선택해 주세요"),
    HOME_IMAGE_NOT_FOUND("이미지를 찾을 수 없습니다."),
    FILE_IO_EXCEPTION("이미지를 불러오지 못했습니다.");

    private String message;

}
