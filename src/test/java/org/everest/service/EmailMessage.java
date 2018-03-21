package org.everest.service;

import dic.AutoInject;
import org.everest.decorator.Component;

@Component
public class EmailMessage extends SendMessage{

    public EmailMessage(String destinator){
        this.destinator = destinator;
    }

    public EmailMessage(){

    }
    @AutoInject
    private Message message;

    private String destinator;

    public String getDestinator() {
        return destinator;
    }
}
