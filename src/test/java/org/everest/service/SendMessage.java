package org.everest.service;

import dic.AutoInject;
import org.everest.core.dic.decorator.AutoWired;

public class SendMessage implements ISendMessage {
    @AutoInject
    public Message message;

    @AutoWired
    private EmailMessage emailMessage;

    public void init(){
        System.out.println("Message: " + message.getMessage());
    }

    @Override
    public void sendMessage() {
        System.out.println("Message: " + message.getMessage());
    }

    public Message getMessage() {
        return message;
    }

    public EmailMessage getEmailMessage() {
        return emailMessage;
    }

}
