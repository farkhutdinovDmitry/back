package ru.dfarkhutdinov.back.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {
    private ErrorCode errorCode;
    private String errorMessage;
}
