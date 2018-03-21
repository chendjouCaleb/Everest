package org.everest.main.component.errorHandler;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

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
    public void handleError(Request request, Response response, Throwable exception) {
        String sep = "<br>&nbsp;&nbsp;&nbsp;&nbsp;";
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        String stacktrace = sw.toString();
        stacktrace = stacktrace.replace(System.getProperty("line.separator"), sep);
        stacktrace = "Error type: " + exception.getClass() + "<br>\n" + stacktrace;
        exception.printStackTrace();
        request.setAttr("stackTrace", stacktrace);
        request.setAttr("exception", exception);
        try {
            response.render(request, "error");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
