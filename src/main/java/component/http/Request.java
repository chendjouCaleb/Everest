package component.http;


import javax.servlet.http.HttpServletRequest;

/**
 * This is wrapper for httpSerletRequest
 * @version 0.0.1
 * @author Chendjou
 */
public class Request {
    HttpServletRequest request;
    public Request(HttpServletRequest request){
        this.request = request;
    }

    /**
     * Check whether request is ajac request
     * @return
     */
    public boolean isXHR(){
        return ((request.getHeader("X-Requested-With") != null) && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }

    /**
     * Check if request is for à specified type
     * @param method is the httpMethod
     * @return
     */
    public boolean isMethod(String method){
        return request.getMethod().equalsIgnoreCase(method);
    }

    /**
     *
     * @return the httpServletRequest for the request
     */
    public HttpServletRequest getRequest() {
        return request;
    }
}
