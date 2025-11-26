package ru.filatov.MySecondTestAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    // Уникальный идентификатор ответа
    private String uid;

    // Идентификатор операции
    private String operationUid;

    // Время системы в формате ISO
    private String systemTime;

    // Код результата выполнения (успех/ошибка)
    private Codes code;

    // Рассчитанная годовая премия (если применимо)
    private Double annualBonus;

    // Код детальной ошибки
    private ErrorCodes errorCode;

    // Текстовое сообщение об ошибке
    private ErrorMessages errorMessage;
}