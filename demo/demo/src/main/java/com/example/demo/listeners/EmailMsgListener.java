package com.example.demo.listeners;

import java.util.EventListener;

/**
 * This class will contain the email of the customers who subscribe to receive something on email
 */
public class EmailMsgListener implements EventListener {
    private final String email;

    public EmailMsgListener(String email){
        this.email = email;
    }

    public void update(){
        //Actually send the email
        System.out.println("Sended email");

    }

}
