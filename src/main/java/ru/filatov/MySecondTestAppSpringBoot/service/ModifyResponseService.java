package ru.filatov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.filatov.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}