package ru.dfarkhutdinov.back.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.dfarkhutdinov.back.exception.error.ErrorCode;

@AllArgsConstructor
@Getter
public class CommonException extends RuntimeException {
    private final ErrorCode code;
    private final String message;
}
