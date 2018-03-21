package org.everest.main;

import org.everest.core.Listener;
import org.everest.core.OnListen;

@OnListen("OnApp")
public class LifeCycleListener implements Listener {

    @OnListen("Start")
    public void onAppStart(){
        System.out.println("Application started...");
    }

    @OnListen("Request")
    public void onRequest(){
        System.out.println("Request was been emitted in app");
    }
}
