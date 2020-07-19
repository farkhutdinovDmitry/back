package ru.dfarkhutdinov.back.exception;

import ru.dfarkhutdinov.back.exception.error.ErrorCode;

public class ConflictException extends CommonException {
    public ConflictException(String message) {
        super(ErrorCode.SAME_NAME, message);
    }
}
