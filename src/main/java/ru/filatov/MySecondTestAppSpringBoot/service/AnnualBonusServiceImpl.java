package ru.filatov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.filatov.MySecondTestAppSpringBoot.model.Positions;
import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        int daysInYear = Year.now().isLeap() ? 366 : 365;
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays) {
        if (!positions.isManager()) {
            throw new IllegalArgumentException("Quarterly bonus is available only for managers");
        }
        int daysInYear = Year.now().isLeap() ? 366 : 365;
        double annual = salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
        return annual / 4.0;
    }
}