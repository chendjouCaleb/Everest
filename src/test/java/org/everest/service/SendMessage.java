package org.everest.service;

import org.everest.core.dic.decorator.AutoWired;
import org.everest.core.dic.decorator.Resolve;

public class SendMessage implements ISendMessage {
    @Resolve
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
