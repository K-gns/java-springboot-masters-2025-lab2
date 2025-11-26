package ru.filatov.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    // Уникальный идентификатор запроса
    @NotBlank(message = "uid must not be blank")
    @Size(max = 32, message = "uid must be at most 32 characters")
    private String uid;

    // Идентификатор операции
    @NotBlank(message = "operationUid must not be blank")
    @Size(max = 32, message = "operationUid must be at most 32 characters")
    private String operationUid;

    // Наименование системы
    private Systems systemName;

    // Время системы в формате ISO
    @NotBlank(message = "systemTime must not be blank")
    private String systemTime;

    // Происхождение/канал
    private String source;

    // Должность
    private Positions position;

    // Оклад
    private Double salary;

    // Коэффициент премии
    private Double bonus;

    // Количество рабочих дней
    private Integer workDays;

    // Идентификатор коммуникации
    @Min(value = 1, message = "communicationId must be at least 1")
    @Max(value = 100000, message = "communicationId must be at most 100000")
    private int communicationId;

    // Идентификатор шаблона
    private int templateId;

    // Код продукта
    private int productCode;

    // Код SMS
    private int smsCode;

    @Override
    public String toString() {
        return "Request{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName=" + systemName +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}