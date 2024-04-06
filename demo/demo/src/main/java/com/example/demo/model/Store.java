package com.example.demo.model;

import com.example.demo.service.NotificationService;

/**
 * This class is the store which contain a notificationService for handling the customers which are subscribed.
 */
public class Store {
    private final NotificationService notificationService;

    public Store(){
        notificationService = new NotificationService();
    }

    public void newSale(){
        notificationService.notifing();
    }


    public NotificationService getNotificationService(){
        return notificationService;
    }
}
