package com.ewallet.notfication;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UnsupportedEncodingException;

public interface NotificationManager {
    public void send(String message) throws JsonProcessingException, UnsupportedEncodingException;
}
