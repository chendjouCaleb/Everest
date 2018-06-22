package org.everest.mvc.model;

import org.everest.mvc.service.message.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    private Map<String, Object> objects = new HashMap<>();
    private Map<String, Object> sessionsObjects = new HashMap<>();

    private List<Message> flashs = new ArrayList<>();

    public void clear(){ objects = new HashMap<>(); }

    public Map<String, Object> getObjects() {
        return objects;
    }

    public void add(String key, Object value){
        objects.put(key, value);
    }
    public void addData(String key, Object value){
        objects.put(key, value);
    }

    public <T> T getObject(String key, Class<? extends T> type){
        return (T) objects.get(key);
    }

    public Object getObject(String key){
        if(objects.containsKey(key)) return objects.get(key); else return null;
    }

    public void addFlash(String content, String type){
        flashs.add(new Message(content, type));
    }

    public void addDangerFlash(String content){ flashs.add(new Message(content, "danger")); }
    public void addInfoFlash(String content){ flashs.add(new Message(content, "info")); }
    public void addSuccessFlash(String content){ flashs.add(new Message(content, "success")); }
    public void addPrimaryFlash(String content){ flashs.add(new Message(content, "primary")); }

    public List<Message> getFlashs() {
        return flashs;
    }

    public Map<String, Object> getSessionsObjects() {
        return sessionsObjects;
    }

    public void addSessionAttribute(String key, Object obj){
        sessionsObjects.put(key, obj);
    }

    public void removeSessionAttribute(String key){
        sessionsObjects.remove(key);
    }
}
