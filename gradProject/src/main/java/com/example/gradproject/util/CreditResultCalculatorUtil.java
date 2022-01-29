package com.example.gradproject.util;

import com.example.gradproject.model.CreditResult;

import java.util.Optional;

public class CreditResultCalculatorUtil {

    private static final int creditLimitFactor=4;

    public static CreditResult calculateCreditResult(int creditNote){
        return creditNote>=500 ? CreditResult.APPROVED : CreditResult.DENIED;
    }
    public static Long calculateCreditLimit(int creditNode, int salary, Optional<Integer> assurance){
        if(calculateCreditResult(creditNode).equals(CreditResult.DENIED)) return 0L;
        if(creditNode>=500 && creditNode<1000){
            if(salary<5000)
                return assurance.map(integer -> (10000L + (int) (integer * 0.1))).orElse(10000L);
            else if(salary<10000)
                return assurance.map(integer -> (20000L + (int) (integer * 0.2))).orElse(20000L);
            else
                return assurance.map(integer -> ((long) creditLimitFactor *salary/2) + (int) (integer * 0.25))
                        .orElse((long) creditLimitFactor *salary/2);
        }
        else{  //(creditNode>=1000)
            return assurance.map(integer -> ((long) creditLimitFactor *salary) + (int) (integer * 0.50))
                    .orElse((long) creditLimitFactor *salary);

        }
    }
}
