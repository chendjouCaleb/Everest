package org.everest.service;

import org.everest.decorator.Component;

@Component("message")
public class Message {
    String message;

    void setMessage(){
        this.message = "Bonjour à tous";
    }

    public String getMessage(){
        return this.message;
    }
}
