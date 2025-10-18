package ru.filatov.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCodes {
    EMPTY("", ""),
    VALIDATION_EXCEPTION("ValidationException", "Ошибка валидации"),
    UNKNOWN_EXCEPTION("UnknownException", "Произошла непредвиденная ошибка"),
    UNSUPPORTED_EXCEPTION("UnsupportedException", "Не поддерживаемая ошибка");

    private final String name;
    private final String description;

    ErrorCodes(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}