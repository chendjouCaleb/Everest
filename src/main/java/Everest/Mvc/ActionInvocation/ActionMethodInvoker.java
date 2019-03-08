package Everest.Mvc.ActionInvocation;

import org.everest.decorator.Instance;
import org.everest.mvc.infrastructure.ActionExecutionException;
import org.everest.utils.Utils;

/**
 * The component that execution action method
 */
@Instance
public class ActionMethodInvoker {
    public ActionInvocationResult invoke(ActionInvocationContext context){
        ActionInvocationResult result = new ActionInvocationResult();

        try {
            Object obj = Utils.callRemote(context.getController(), context.getMethod(), context.getParams());
            if(context.getMethod().getReturnType().equals(void.class)) {
                result.setVoid(true);
                return result;
            }


            if(obj == null){
                result.setNull(true);
                return result;
            }
            result.setObjectResult(obj);
        } catch (Exception e) {
            throw new ActionExecutionException(e);
        }
        return result;
    }
}
