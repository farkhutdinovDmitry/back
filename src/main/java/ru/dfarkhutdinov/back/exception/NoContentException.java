package ru.dfarkhutdinov.back.exception;

import ru.dfarkhutdinov.back.exception.error.ErrorCode;

public class NoContentException extends CommonException {
    public NoContentException() {
        super(ErrorCode.NO_CONTENT, "Content not found");
    }
}
