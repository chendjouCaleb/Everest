package Everest.Http;

public class DefaultHttpContext extends HttpContext {

    public DefaultHttpContext(){
        super();
        response = new DefaultHttpResponse();
        request = new DefaultHttpRequest();
    }
}
