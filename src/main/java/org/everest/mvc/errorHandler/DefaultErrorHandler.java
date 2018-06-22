package org.everest.mvc.errorHandler;

import org.everest.mvc.httpContext.HttpContext;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class DefaultErrorHandler implements IErrorHandler<Throwable> {
    @Override
    public Class getErrorType() {
        return null;
    }

    @Override
    public void handleError(HttpContext httpContext, Throwable exception) {
        String sep = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String stacktrace = sw.toString();
        httpContext.getRequest().addAttribute("traces", stacktrace.split(System.getProperty("line.separator")));
        stacktrace = stacktrace.replace(System.getProperty("line.separator"), sep);
        stacktrace = "Error type: " + exception.getClass() + "<br>\n" + stacktrace;
        exception.printStackTrace();
        httpContext.getRequest().addAttribute("stackTrace", stacktrace);
        httpContext.getRequest().addAttribute("exception", exception);
        httpContext.getRequest().addAttribute("type", exception.getClass().getCanonicalName());


        try {
            httpContext.getResponse().render(httpContext.getRequest(), "error");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
