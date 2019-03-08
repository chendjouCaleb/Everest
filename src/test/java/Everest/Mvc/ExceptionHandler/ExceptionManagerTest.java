package Everest.Mvc.ExceptionHandler;

import Everest.Core.Exception.InvalidOperationException;
import Everest.Http.DefaultHttpContext;
import Everest.Http.HttpContext;
import Everest.Mvc.ActionResultExecutor.ActionResultExecutor;
import Everest.Mvc.ActionResultExecutor.ActionResultExecutorProvider;
import Everest.Mvc.ActionResultExecutor.IResultExecutor;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.everest.mvc.result.ActionResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionManagerTest {

    private ActionResultExecutorProvider executorProvider;
    private ExceptionHandlerProvider handlerProvider;
    private ActionResultExecutor resultExecutor;
    private ExceptionManager exceptionManager;

    @BeforeEach
    void setUp() {
        handlerProvider = new ExceptionHandlerProvider();
        executorProvider = new ActionResultExecutorProvider();
        resultExecutor = new ActionResultExecutor(executorProvider);
        exceptionManager = new ExceptionManager(resultExecutor, handlerProvider, new DefaultExceptionHandler(new ExceptionStatusCodeGetter()));

        handlerProvider.addExceptionHandler(new InvalidOperationExceptionHandler());
        handlerProvider.addExceptionHandler(new NoSuchElementExceptionHandler());

        executorProvider.addExecutor(new SimpleResultExecutor());
    }

    @Test
    void handleError_With_Null_Result() {
        InvalidOperationException invalidOperationException = new InvalidOperationException("message of exception");
        HttpContext httpContext = new DefaultHttpContext();

        exceptionManager.handleError(invalidOperationException, httpContext);

        assertEquals(httpContext.getResponse().writer().toString(), invalidOperationException.getMessage());
    }

    @Test
    void handleException_With_ResultContext(){
        NoSuchElementException exception = new NoSuchElementException("Element not found");
        HttpContext httpContext = new DefaultHttpContext();

        exceptionManager.handleError(exception, httpContext);
        assertEquals(httpContext.getResponse().writer().toString(), exception.getMessage());
        assertEquals(404, httpContext.getResponse().statusCode());

    }

    private class InvalidOperationExceptionHandler implements IExceptionHandler{
        public Collection<Class<? extends Throwable>> getExceptionTypes() {
           return Collections.singletonList(InvalidOperationException.class);
        }

        public void handleException(ExceptionContext context) {
            context.getHttpContext().getResponse().write(context.getException().getMessage());
        }
    }

    private class NoSuchElementExceptionHandler implements IExceptionHandler{
        public Collection<Class<? extends Throwable>> getExceptionTypes() {
            return Collections.singletonList(NoSuchElementException.class);
        }

        public void handleException(ExceptionContext context) {
            SimpleResult result = new SimpleResult(context.getException().getMessage(), 404);
        }
    }

    private class SimpleResultExecutor implements IResultExecutor<SimpleResult>{

        @Override
        public void execute(HttpContext httpContext, SimpleResult result) {
            httpContext.getResponse().write(result.getMessage());
            httpContext.getResponse().setStatusCode(result.getStatusCode());
        }
    }

    private class SimpleResult{
        private String message;
        protected int statusCode;

        public SimpleResult(String message, int statusCode) {
            this.message = message;
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }
    }
}