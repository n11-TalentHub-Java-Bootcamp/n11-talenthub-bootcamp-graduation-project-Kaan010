package com.example.gradproject.util;

public class CreditNoteUtil {
    public static int calculte(long identificationNo){
        return (int)((identificationNo%1000)+100);
    }
}
