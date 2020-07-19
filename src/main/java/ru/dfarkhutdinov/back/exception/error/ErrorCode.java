package ru.dfarkhutdinov.back.exception.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    NO_CONTENT(HttpStatus.NO_CONTENT),
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    SAME_NAME(HttpStatus.CONFLICT);

    private final HttpStatus status;

    ErrorCode(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

