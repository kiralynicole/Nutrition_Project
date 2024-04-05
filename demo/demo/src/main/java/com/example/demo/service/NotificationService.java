package com.example.demo.service;

import com.example.demo.listeners.EmailMsgListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private final List<EmailMsgListener> customers;

    public NotificationService(){
        customers = new ArrayList<>();
    }

    public void subscribe(EmailMsgListener listener){
        customers.add(listener);
    }

    public void unsubscribe(EmailMsgListener listener){
        customers.remove(listener);
    }
}
