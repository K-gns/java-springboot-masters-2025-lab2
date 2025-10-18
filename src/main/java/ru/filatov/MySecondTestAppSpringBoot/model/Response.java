package ru.filatov.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;
import ru.filatov.MySecondTestAppSpringBoot.model.Codes;
import ru.filatov.MySecondTestAppSpringBoot.model.ErrorCodes;
import ru.filatov.MySecondTestAppSpringBoot.model.ErrorMessages;

@Data
@Builder
public class Response {
    private String uid;
    private String operationUid;
    private String systemTime;
    private Codes code;
    private ErrorCodes errorCode;
    private ErrorMessages errorMessage;
}