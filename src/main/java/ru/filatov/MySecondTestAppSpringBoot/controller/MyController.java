package ru.filatov.MySecondTestAppSpringBoot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.filatov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.filatov.MySecondTestAppSpringBoot.model.*;
import ru.filatov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.filatov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.filatov.MySecondTestAppSpringBoot.util.ControllerValidationHelper;
import ru.filatov.MySecondTestAppSpringBoot.util.ResponseFactory;


@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ControllerValidationHelper validationHelper;
    private final ResponseFactory responseFactory;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        ControllerValidationHelper validationHelper,
                        ResponseFactory responseFactory) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.validationHelper = validationHelper;
        this.responseFactory = responseFactory;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request,
                                             BindingResult bindingResult) {

        log.info("Получен Request: {}", request);

        // Правило для uid == "123"
        if ("123".equals(request.getUid())) {
            Response errorResponse = responseFactory.createUnsupportedResponse(request);
            log.info("Сформирован ответ с ошибкой 'UnsupportedCodeException': {}", errorResponse);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // Базовый ответ
        Response response = responseFactory.createBaseResponse(request);
        log.info("Создан базовый Response: {}", response);

        // Валидация и получаем статус/ответ при ошибке
        try {
            validationHelper.validateOrThrow(validationService, bindingResult);
        } catch (ValidationFailedException e) {
            Response validationError = responseFactory.createValidationFailedResponse(response);
            log.info("Сформирован ответ с ошибкой валидации: {}", validationError);
            return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Response unknownError = responseFactory.createUnknownErrorResponse(response);
            log.info("Сформирован ответ с неизвестной ошибкой: {}", unknownError);
            return new ResponseEntity<>(unknownError, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Модификация
        Response modified = modifyResponseService.modify(response);
        log.info("Ответ после модификации: {}", modified);

        return new ResponseEntity<>(modified, HttpStatus.OK);
    }
}