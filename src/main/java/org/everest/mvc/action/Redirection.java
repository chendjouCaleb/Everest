package org.everest.mvc.action;

public class Redirection extends Action{
    public String url;

    public Redirection(String url) {
        this.url = url;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
