package org.everest.dic.test;

import dic.AutoInject;
import dic.OnInit;

public class SendMessage implements ISendMessage{
    @AutoInject
    private Message message;

    @OnInit
    public void init(){
        System.out.println("Message: " + message.getMessage());
    }

    @Override
    public void sendMessage() {
        System.out.println("Message: " + message.getMessage());
    }
}
