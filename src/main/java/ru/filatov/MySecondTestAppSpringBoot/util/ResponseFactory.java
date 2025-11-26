package ru.filatov.MySecondTestAppSpringBoot.util;

import org.springframework.stereotype.Component;
import ru.filatov.MySecondTestAppSpringBoot.model.*;

import java.util.Date;

@Component
public class ResponseFactory {

    public Response createBaseResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    public Response createUnsupportedResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code(Codes.FAILED)
                .errorCode(ErrorCodes.UNSUPPORTED_EXCEPTION)
                .errorMessage(ErrorMessages.UNSUPPORTED)
                .build();
    }

    public Response createValidationFailedResponse(Response base) {
        return Response.builder()
                .uid(base.getUid())
                .operationUid(base.getOperationUid())
                .systemTime(base.getSystemTime())
                .code(Codes.FAILED)
                .errorCode(ErrorCodes.VALIDATION_EXCEPTION)
                .errorMessage(ErrorMessages.VALIDATION)
                .build();
    }

    public Response createUnknownErrorResponse(Response base) {
        return Response.builder()
                .uid(base.getUid())
                .operationUid(base.getOperationUid())
                .systemTime(base.getSystemTime())
                .code(Codes.FAILED)
                .errorCode(ErrorCodes.UNKNOWN_EXCEPTION)
                .errorMessage(ErrorMessages.UNKNOWN)
                .build();
    }
}

