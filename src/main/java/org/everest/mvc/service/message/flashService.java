package org.everest.mvc.service.message;

import org.everest.mvc.httpContext.Request;

public class flashService {
    public void sendFlash(Request request, String message, String type){
        new Message(message, type);
    }

    public void sendSuccessFlash(Request request, String message){
        new Message(message, "success");
    }

    public void sendDangerFlash(Request request, String message){
        new Message(message, "danger");
    }

    public void sendWarningFlash(Request request, String message){
        new Message(message, "primary");
    }

    public void sendInfoFlash(Request request, String message){
        new Message(message, "info");
    }
}
