package com.example.demo.service;

import com.example.demo.listeners.EmailMsgListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This class should keep track of the customers or subscribers or listeners
 */

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


    public void notifing(){
        customers.forEach(listener -> listener.update());
    }

    public List<EmailMsgListener> getCustomers() {
        return customers;
    }
}
