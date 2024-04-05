package com.example.demo.model;

import com.example.demo.service.NotificationService;

public class Store {
    private final NotificationService notificationService;

    public Store(){
        notificationService = new NotificationService();
    }


    public NotificationService getNotificationService(){
        return notificationService;
    }
}
