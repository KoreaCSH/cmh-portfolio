package com.choimyeongheon.portfolio.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomException extends RuntimeException {

    private ErrorType errorType;

    @Override
    public String getMessage() {
        return errorType.getMessage();
    }
}
