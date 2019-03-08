package org.everest.service;

import org.everest.core.dic.decorator.Resolve;
import org.everest.decorator.Component;

@Component
public class EmailMessage extends SendMessage{

    public EmailMessage(String destinator){
        this.destinator = destinator;
    }

    public EmailMessage(){

    }
    @Resolve
    private Message message;

    private String destinator;

    public String getDestinator() {
        return destinator;
    }
}
