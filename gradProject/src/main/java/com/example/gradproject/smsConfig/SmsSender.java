package com.example.gradproject.smsConfig;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
    // or maybe void sendSms(String phoneNumber, String message);
}