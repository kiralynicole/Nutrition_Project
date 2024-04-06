package com.example.demo.model;

import com.example.demo.service.NotificationService;

/**
 * The {@code Store} class represents a store that contains a {@link NotificationService} to manage
 * notifications for subscribed customers. It is responsible for notifying customers about new sales
 * or promotions via the {@code NotificationService}.
 */
public class Store {
    private final NotificationService notificationService;

    /**
     * Constructs a new {@code Store} instance with its own {@link NotificationService}.
     * The {@code NotificationService} is used to manage subscriptions and notifications
     * for customers interested in receiving updates about new sales.
     */
    public Store() {
        notificationService = new NotificationService();
    }

    /**
     * Notifies all subscribed customers of a new sale. This method utilizes the
     * {@link NotificationService} to send out notifications to all customers who
     * have subscribed to receive updates.
     */
    public void newSale() {
        notificationService.notifing();
    }

    /**
     * Retrieves the {@link NotificationService} associated with this {@code Store}.
     * This service is responsible for managing customer subscriptions and notifications.
     *
     * @return The {@link NotificationService} used by this store to manage notifications.
     */
    public NotificationService getNotificationService() {
        return notificationService;
    }
}
