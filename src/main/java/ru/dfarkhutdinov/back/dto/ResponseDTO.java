package ru.dfarkhutdinov.back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private T response;

    public static <T> ResponseDTO<T> createResponse(T response) {
        return new ResponseDTO<>(response);
    }
}
