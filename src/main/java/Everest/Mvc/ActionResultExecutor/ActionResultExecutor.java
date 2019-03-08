package Everest.Mvc.ActionResultExecutor;

import Everest.Http.HttpContext;
import Everest.Http.HttpResponse;
import Everest.Http.StatusCode;
import Everest.Mvc.Result.EntityResult;
import org.everest.decorator.Instance;
import Everest.Mvc.ActionInvocation.ActionInvocationResult;

/**
 * The component that execute the result returned by an action methods
 */
@Instance
public class ActionResultExecutor {

    /**
     * The {@link IResultExecutor} instance provider.
     */
    private ActionResultExecutorProvider executorProvider;

    public ActionResultExecutor(ActionResultExecutorProvider executorProvider) {
        this.executorProvider = executorProvider;
    }

    /**
     * Execute the result of an action method
     * @param context The {@link HttpContext } of the http request
     * @param result The result returned by the action method invocation
     */
    public void execute(HttpContext context, ActionInvocationResult result){
        HttpResponse response = context.getResponse();
        if(result.isVoid()){
            response.setStatusCode(StatusCode.NO_CONTENT.value());
            response.write("");
            return;
        }
        //If the result is null, we assign the string 'null' to result prior to continue the execution
        if(result.getObjectResult() == null){
            result.setObjectResult("null");
        }

        Class type = result.getResultType();

        IResultExecutor resultExecutor = executorProvider.getExecutor(type);

        /*
         * If there are no resultExecutor for the type of the result
         * The EntityResultExecutor is selected and the result become an EntityResult with the old result as setBody.
         */
        if(resultExecutor == null){
            resultExecutor = executorProvider.getExecutor(EntityResult.class);
            EntityResult<?> entityResult = new EntityResult(result.getObjectResult());
            result.setObjectResult(entityResult);
        }

        resultExecutor.execute(context, result.getObjectResult());
    }
}
