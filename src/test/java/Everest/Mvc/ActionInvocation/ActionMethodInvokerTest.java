package Everest.Mvc.ActionInvocation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ActionMethodInvokerTest {
    private ActionMethodInvoker invoker = new ActionMethodInvoker();
    private TestController controller = new TestController();

    @Test
    void voidMethod() throws NoSuchMethodException {
        Method method = TestController.class.getMethod("voidMethod", String.class);
        ActionInvocationContext context = new ActionInvocationContext(controller, method, new Object[]{"message"});
        ActionInvocationResult result = invoker.invoke(context);

        assertTrue(result.isVoid());
        assertTrue(controller.voidMethodIsInvoked);
    }

    @Test
    void nullInvocationResult() throws NoSuchMethodException {
        Method method = TestController.class.getMethod("toUpper", String.class);
        ActionInvocationContext context = new ActionInvocationContext(controller, method, new Object[]{"message"});
        ActionInvocationResult result = invoker.invoke(context);

        assertEquals("MESSAGE", result.getObjectResult());
        assertFalse(result.isVoid());
    }

}

class TestController{
    boolean voidMethodIsInvoked;

    public void voidMethod(String message){
        System.out.println(message);
        voidMethodIsInvoked = true;
    }

    public String toUpper(String message){
        return message.toUpperCase();
    }
}