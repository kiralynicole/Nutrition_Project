package com.example.demo.listeners;

import java.util.EventListener;

/**
 * The {@code EmailMsgListener} class implements {@link EventListener} to provide a mechanism
 * for handling email notifications. Each instance of this class is associated with a customer's
 * email address. When notified, it simulates the action of sending an email to this address.
 */
public class EmailMsgListener implements EventListener {
    private final String email;

    /**
     * Constructs a new {@code EmailMsgListener} with the specified email address.
     *
     * @param email The email address of the subscriber who will receive notifications.
     */
    public EmailMsgListener(String email) {
        this.email = email;
    }

    /**
     * Simulates sending an email to the subscriber. In a real application, this method would
     * include logic to actually send an email. Currently, it prints a message to the console.
     */
    public void update() {
        // Actually send the email
        System.out.println("Update sale!!");
    }

    /**
     * Retrieves the email address associated with this listener.
     *
     * @return The email address of this listener.
     */
    public String getEmail() {
        return email;
    }
}
