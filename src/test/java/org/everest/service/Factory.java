package org.everest.service;

import org.everest.core.dic.decorator.Bean;


public class Factory {


    @Bean
    public EmailMessage emailMessage() { return new EmailMessage();}

    @Bean
    public StarCreator starCreator(){ return new StarCreator(); }



    @Bean("message")
    public Message message(){ return new Message();}
}
