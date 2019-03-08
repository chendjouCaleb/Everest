package org.everest.mvc.service.message;

import Everest.Http.HttpRequest;

public class flashService {
    public void sendFlash(HttpRequest httpRequest, String message, String type){
        new Message(message, type);
    }

    public void sendSuccessFlash(HttpRequest httpRequest, String message){
        new Message(message, "success");
    }

    public void sendDangerFlash(HttpRequest httpRequest, String message){
        new Message(message, "danger");
    }

    public void sendWarningFlash(HttpRequest httpRequest, String message){
        new Message(message, "primary");
    }

    public void sendInfoFlash(HttpRequest httpRequest, String message){
        new Message(message, "info");
    }
}
