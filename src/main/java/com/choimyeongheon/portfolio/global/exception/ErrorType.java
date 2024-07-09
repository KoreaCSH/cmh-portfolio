package com.choimyeongheon.portfolio.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    EMPTY_IMAGE_DELETION_LIST("삭제할 이미지를 선택해 주세요"),
    IMAGE_NOT_FOUND("이미지를 찾을 수 없습니다."),
    FILE_IO_EXCEPTION("이미지를 불러오지 못했습니다."),
    FILE_UPLOAD_EXCEPTION("파일을 저장하지 못했습니다."),
    WORK_NOT_FOUND("이미지를 찾을 수 없습니다."),
    PROFILE_NOT_FOUND("약력을 찾을 수 없습니다."),
    EMPTY_PROFILE_DELETION_LIST("삭제할 약력을 선택해 주세요"),
    CONTACT_NOT_FOUND("메일을 찾을 수 없습니다."),
    EMPTY_CONTACT_DELETION_LIST("삭제할 메일을 선택해 주세요"),
    EMPTY_WORK_YEAR_OR_DUPLICATED("연도를 입력하지 않았거나, 이미 저장된 연도입니다."),

    DUPLICATED_ADMIN("가입할 수 없는 아이디입니다.");

    private String message;

}
