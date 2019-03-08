package Everest.Mvc.ExceptionHandler;

import Everest.Mvc.ActionInvocation.ActionInvocationResult;
import Everest.Mvc.ActionResultExecutor.ActionResultExecutor;
import org.everest.core.dic.Container;
import org.everest.core.dic.decorator.AfterContainerInitilized;
import org.everest.decorator.Instance;
import Everest.Http.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

@Instance
public class ExceptionManager {
    private Logger logger = LoggerFactory.getLogger(ExceptionManager.class);
    private ActionResultExecutor resultExecutor;
    private ExceptionHandlerProvider handlerProvider;
    private DefaultExceptionHandler defaultExceptionHandler;

    public ExceptionManager(ActionResultExecutor resultExecutor, ExceptionHandlerProvider handlerProvider, DefaultExceptionHandler defaultExceptionHandler) {
        this.resultExecutor = resultExecutor;
        this.handlerProvider = handlerProvider;
        this.defaultExceptionHandler = defaultExceptionHandler;
    }

    public void handleError(Throwable exception, HttpContext httpContext){
        Throwable rootException = exception;
        while (rootException.getCause() != null) {
            rootException = rootException.getCause();
        }

        logger.info("The {} was thrown: ", rootException.getClass());
        logger.error("Erreur durant l'exécution de la requete.", rootException);

        ExceptionContext exceptionContext = new ExceptionContext(rootException, httpContext);

        try {
            IExceptionHandler exceptionHandler = handlerProvider.getExceptionHandler(rootException.getClass());
            exceptionHandler.handleException(exceptionContext);
        }catch (NoSuchElementException e){
            defaultExceptionHandler.handleException(exceptionContext);
        }


        if(exceptionContext.getResult() != null){
            ActionInvocationResult result = new ActionInvocationResult();
            result.setObjectResult(exceptionContext.getResult());
            resultExecutor.execute(httpContext, result);
        }


    }

    @AfterContainerInitilized
    public void setDefaultErrorHandler(Container container) {
        logger.info("The exception event manager is ok!");
    }
}
