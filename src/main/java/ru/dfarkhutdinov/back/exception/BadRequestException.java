package ru.dfarkhutdinov.back.exception;

import ru.dfarkhutdinov.back.exception.error.ErrorCode;

public class BadRequestException extends CommonException {
    public BadRequestException(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }
}
