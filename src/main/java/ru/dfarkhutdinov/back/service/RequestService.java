package ru.dfarkhutdinov.back.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dfarkhutdinov.back.db.RequestRepository;
import ru.dfarkhutdinov.back.entity.Request;
import ru.dfarkhutdinov.back.exception.CommonException;
import ru.dfarkhutdinov.back.exception.ConflictException;

import java.util.List;

@AllArgsConstructor
@Service
public class RequestService {
    private final RequestRepository requestRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request addRequest(Request request) throws CommonException {
        if (request.getBanner() == null) {
            throw new ConflictException("Request cannot be null");
        }
        return requestRepository.saveAndFlush(request);
    }
}
