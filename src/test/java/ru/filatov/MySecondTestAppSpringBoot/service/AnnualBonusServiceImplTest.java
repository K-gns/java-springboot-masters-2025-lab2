package ru.filatov.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.filatov.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {

        // given
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays);

        // then
        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyBonusForManager() {
        // given
        Positions position = Positions.TL; // manager
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // compute expected using same logic (includes leap year handling)
        int daysInYear = Year.now().isLeap() ? 366 : 365;
        double expectedAnnual = salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
        double expectedQuarterly = expectedAnnual / 4.0;

        // when
        double result = new AnnualBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays);

        // then
        assertThat(result).isEqualTo(expectedQuarterly);
    }

    @Test
    void calculateQuarterlyBonusForNonManagerThrows() {
        // given
        Positions position = Positions.DEV; // not a manager
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when / then
        assertThrows(IllegalArgumentException.class, () ->
                new AnnualBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays)
        );
    }
}