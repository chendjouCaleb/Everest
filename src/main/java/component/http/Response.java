package component.http;

import javax.servlet.http.HttpServletResponse;

public class Response {

    HttpServletResponse response;
    public Response(HttpServletResponse response){
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
