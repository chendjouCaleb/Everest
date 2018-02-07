package org.everest.dic.test;

import dic.OnInit;

public class Message {
    String message;

    @OnInit
     void setMessage(){
        this.message = "Bonjour à tous";
    }

    public String getMessage(){
        return this.message;
    }
}
