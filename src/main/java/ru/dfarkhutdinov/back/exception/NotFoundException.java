package ru.dfarkhutdinov.back.exception;

import ru.dfarkhutdinov.back.exception.error.ErrorCode;

public class NotFoundException extends CommonException {
    public NotFoundException() {
        super(ErrorCode.NOT_FOUND, "Resource not found");
    }
}
