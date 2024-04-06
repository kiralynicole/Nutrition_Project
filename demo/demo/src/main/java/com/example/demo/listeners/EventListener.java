package com.example.demo.listeners;

/**
 * The {@code EventListener} interface defines a single method, {@code update}, which is intended
 * to be implemented by classes that act as listeners for specific events within the application.
 * Implementing classes should provide their own logic for how to respond when the {@code update}
 * method is called, typically as a result of an event occurrence.
 */
public interface EventListener {
    /**
     * This method is called to notify the listener of an event occurrence. Implementing classes
     * should provide the specific logic for handling this notification, which may include actions
     * such as updating an internal state, initiating some processing, or performing a specific task
     * in response to the event.
     */
    void update();
}
