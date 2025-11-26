package ru.filatov.MySecondTestAppSpringBoot.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import ru.filatov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.filatov.MySecondTestAppSpringBoot.service.ValidationService;

@Component
public class ControllerValidationHelper {

    public void validateOrThrow(ValidationService validationService, BindingResult bindingResult) throws ValidationFailedException {
        validationService.isValid(bindingResult);
    }
}
