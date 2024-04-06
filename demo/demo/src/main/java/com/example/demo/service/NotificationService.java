package com.example.demo.service;

import com.example.demo.listeners.EmailMsgListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code NotificationService} class manages the registration and notification of {@link EmailMsgListener}
 * instances. It allows for subscribing and unsubscribing listeners to email messages and notifying all
 * registered listeners when an event occurs that they should be informed about.
 */
public class NotificationService {
    private final List<EmailMsgListener> customers;

    /**
     * Initializes a new {@code NotificationService} with an empty list of customers or subscribers.
     */
    public NotificationService() {
        customers = new ArrayList<>();
    }

    /**
     * Subscribes a new listener to the notification service.
     *
     * @param listener The {@link EmailMsgListener} to be added to the list of subscribers.
     */
    public void subscribe(EmailMsgListener listener) {
        customers.add(listener);
    }

    /**
     * Unsubscribes an existing listener from the notification service.
     *
     * @param listener The {@link EmailMsgListener} to be removed from the list of subscribers.
     */
    public void unsubscribe(EmailMsgListener listener) {
        customers.remove(listener);
    }

    /**
     * Notifies all subscribed listeners by calling their update method. This method is typically
     * called to notify all listeners of a particular event or update they should be aware of.
     */
    public void notifing() {
        customers.forEach(EmailMsgListener::update);
    }

    /**
     * Gets the list of currently subscribed listeners.
     *
     * @return A {@link List} of {@link EmailMsgListener} that are currently subscribed.
     */
    public List<EmailMsgListener> getCustomers() {
        return customers;
    }
}
