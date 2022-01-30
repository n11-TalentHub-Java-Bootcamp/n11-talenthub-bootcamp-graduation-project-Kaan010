package com.example.gradproject.smsConfig;

import com.twilio.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
@org.springframework.stereotype.Service
public class SmsService {
    private final SmsSender smsSender;
    @Autowired
    public SmsService(@Qualifier("twilio") TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }
    public boolean sendSms(SmsRequest smsRequest) throws ApiException {
        smsSender.sendSms(smsRequest);
        return true;
    }
}