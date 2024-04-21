package com.example.demo.notification;


import com.example.demo.listeners.EmailMsgListener;
import com.example.demo.service.NotificationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.ExpectedCount.times;

/**
 * The {@code NotificationTest} class contains tests for verifying the functionality
 * of the {@link NotificationService} which manages notification subscriptions.
 * This class utilizes JUnit for testing the service's ability to manage (subscribe or unsubscribe)
 * listeners and to notify them correctly.
 *
 * Each test method in this class is designed to test a specific aspect of the {@link NotificationService}.
 * Mocking is done via Mockito to simulate the interaction with {@link EmailMsgListener} instances.
 */

@SpringBootTest
public class NotificationTest {

    @Mock
    EmailMsgListener emailMsgListener;

    NotificationService notificationService;

    /**
     * Sets up the test environment before each test method.
     * This method initializes mocks and creates a new instance of {@link NotificationService}.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        notificationService = new NotificationService();
    }

    /**
     * Tests that an {@link EmailMsgListener} can be successfully subscribed to the {@link NotificationService}.
     * After subscription, the listener should be present in the list of listeners managed by the service.
     */
    @Test
    public void subscribeTest() {
        notificationService.subscribe(emailMsgListener);
        assertTrue(notificationService.getCustomers().contains(emailMsgListener));
    }

    /**
     * Tests that an {@link EmailMsgListener} can be successfully unsubscribed from the {@link NotificationService}.
     * After unsubscription, the listener should not be present in the list of listeners managed by the service.
     */
    @Test
    public void unsubscribeTest() {
        notificationService.subscribe(emailMsgListener);
        notificationService.unsubscribe(emailMsgListener);
        assertFalse(notificationService.getCustomers().contains(emailMsgListener));
    }

    /**
     * Tests that all subscribed {@link EmailMsgListener}s are notified when the {@link NotificationService} dispatches a notification.
     * This test ensures that the update method is called on every listener once a notification is sent.
     */
    @Test
    public void notifyingTest() {
        EmailMsgListener anotherListener = mock(EmailMsgListener.class);
        notificationService.subscribe(emailMsgListener);
        notificationService.subscribe(anotherListener);

        notificationService.notifing();

        verify(emailMsgListener).update();
        verify(anotherListener).update();
    }





}
