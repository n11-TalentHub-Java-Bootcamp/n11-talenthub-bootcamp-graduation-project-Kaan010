package com.example.gradproject.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

class CreditResultCalculatorUtilTest {

    private CreditResultCalculatorUtil creditResultCalculatorUtil;

    @BeforeEach
    void setUp(){
        creditResultCalculatorUtil=new CreditResultCalculatorUtil();
    }

    @Test
    void testLimitOfCredit_whenSalaryLessThan500_ShouldReturnZero() {
        Long expectedResult=0L;

        int creditNote1=1;
        int creditNote2=499;

        Long actualResult1 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote1, anyInt(), Optional.empty());
        assertEquals(expectedResult,actualResult1);
        Long actualResult2 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote2, anyInt(), Optional.of(anyInt()));
        assertEquals(expectedResult,actualResult2);
    }

    @Test
    void testLimitOfCredit_whenCreditNoteBetween500And1000SalaryLessThan5000_ShouldReturn10000PlusTenPercentageOfAssurance() {
        Long expectedResult=10000L;

        int creditNote1=500;
        int salary1=100;
        int assurance1=anyInt();
        Long actualResult1 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote1,salary1, Optional.empty());
        assertEquals(expectedResult,actualResult1);
        Long actualResult2 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote1,salary1, Optional.of(assurance1));
        assertEquals((expectedResult+(long)(assurance1*0.1)),actualResult2);

        int creditNote2=999;
        int salary2=4999;
        int assurance2=anyInt();
        Long actualResult3 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote2,salary2, Optional.empty());
        assertEquals(expectedResult,actualResult3);
        Long actualResult4 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote2,salary2, Optional.of(assurance2));
        assertEquals((expectedResult+(long)(assurance2*0.1)), actualResult4);
    }

    @Test
    void testLimitOfCredit_whenCreditNoteBetween500And1000SalaryLessThan10000_ShouldReturn20000PlusTenPercentageOfAssurance() {
        Long expectedResult=20000L;

        int creditNote1=500;
        int salary1=5000;
        int assurance1=anyInt();
        Long actualResult1 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote1,salary1, Optional.empty());
        assertEquals(expectedResult,actualResult1);
        Long actualResult2 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote1,salary1, Optional.of(assurance1));
        assertEquals((expectedResult+(long)(assurance1*0.1)),actualResult2);

        int creditNote2=999;
        int salary2=9999;
        int assurance2=anyInt();
        Long actualResult3 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote2,salary2, Optional.empty());
        assertEquals(expectedResult,actualResult3);
        Long actualResult4 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote2,salary2, Optional.of(assurance2));
        assertEquals((expectedResult+(long)(assurance2*0.1)), actualResult4);
    }

    @Test
    void testLimitOfCredit_whenCreditNoteBetween500And1000SalaryBiggerThan10000_ShouldReturnFormulasHalfPlus25PercentageOfAssurance() {
        Function<Integer, Long> funcWithoutAssurance = salary -> (long)salary*4/2;
        BiFunction<Integer, Integer, Long> funcWithAssurance = (assurance, salary) -> (long)((salary*4/2)+assurance*0.25);

        int creditNote1=500;
        int salary1=10000;
        int assurance1=anyInt();
        Long actualResult1 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote1,salary1, Optional.empty());
        assertEquals(funcWithoutAssurance.apply(salary1),actualResult1);
        Long actualResult2 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote1,salary1, Optional.of(assurance1));
        assertEquals(funcWithAssurance.apply(assurance1,salary1),actualResult2);

        int creditNote2=999;
        int salary2=15000;
        int assurance2=anyInt();
        Long actualResult3 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote2,salary2, Optional.empty());
        assertEquals(funcWithoutAssurance.apply(salary2),actualResult3);
        Long actualResult4 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote2,salary2, Optional.of(assurance2));
        assertEquals(funcWithAssurance.apply(assurance2,salary2),actualResult4);
    }

    @Test
    void testLimitOfCredit_whenCreditNoteBiggerThan1000_ShouldReturnFormulaPlus50PercentageOfAssurance() {
        Function<Integer, Long> funcWithoutAssurance = salary -> (long)salary*4;
        BiFunction<Integer, Integer, Long> funcWithAssurance = (assurance, salary) -> (long)((salary*4)+assurance*0.50);

        int creditNote1=1000;
        int salary1=1000;
        int assurance1=anyInt();
        Long actualResult1 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote1,salary1, Optional.empty());
        assertEquals(funcWithoutAssurance.apply(salary1),actualResult1);
        Long actualResult2 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote1,salary1, Optional.of(assurance1));
        assertEquals(funcWithAssurance.apply(assurance1,salary1),actualResult2);

        int creditNote2=1500;
        int salary2=15000;
        int assurance2=anyInt();
        Long actualResult3 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote2,salary2, Optional.empty());
        assertEquals(funcWithoutAssurance.apply(salary2),actualResult3);
        Long actualResult4 = CreditResultCalculatorUtil.calculateCreditLimit(creditNote2,salary2, Optional.of(assurance2));
        assertEquals(funcWithAssurance.apply(assurance2,salary2),actualResult4);
    }


}