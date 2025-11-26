package ru.filatov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.filatov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.filatov.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService {

    double calculate(Positions positions, double salary, double bonus, int workDays);

    // Добавляет расчёт квартальной премии для менеджеров
    double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays) throws ValidationFailedException;

}