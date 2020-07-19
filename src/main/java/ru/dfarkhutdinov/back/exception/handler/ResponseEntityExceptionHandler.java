package ru.dfarkhutdinov.back.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dfarkhutdinov.back.dto.ResponseDTO;
import ru.dfarkhutdinov.back.exception.CommonException;
import ru.dfarkhutdinov.back.exception.error.ResponseError;

@RestControllerAdvice
public class ResponseEntityExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(CommonException ex) {
        ResponseDTO<Object> responseBody = ResponseDTO.createResponse(new ResponseError(ex.getCode(), ex.getMessage()));
        return ResponseEntity.status(ex.getCode().getStatus()).body(responseBody);
    }
}
