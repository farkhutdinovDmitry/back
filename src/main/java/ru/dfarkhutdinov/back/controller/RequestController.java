package ru.dfarkhutdinov.back.controller;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dfarkhutdinov.back.dto.ResponseDTO;
import ru.dfarkhutdinov.back.entity.Request;
import ru.dfarkhutdinov.back.service.RequestService;

import java.util.List;

@RestController
@AllArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/requests/all")
    public ResponseDTO<List<Request>> getRequests() {
        return ResponseDTO.createResponse(requestService.getAllRequests());
    }

    @PostMapping("requests/add")
    public ResponseDTO<Request> addRequest(@RequestBody Request request) {
        val created = requestService.addRequest(request);
        return ResponseDTO.createResponse(created);
    }
}
