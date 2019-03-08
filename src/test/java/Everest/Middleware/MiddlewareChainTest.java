package Everest.Middleware;

import Everest.Http.DefaultHttpContext;
import Everest.Http.HttpContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiddlewareChainTest {
    private MiddlewareChain middlewareChain = new MiddlewareChain();

    @Test
    void Chain_Creation() {
        MiddlewareChain chain = new MiddlewareChain();
        assertEquals(0, chain.getCurrent());
        assertEquals(0, chain.getMiddlewares().size());
    }

    @Test
    void executeNext_With_One_Middleware() {
        HttpContext httpContext = new DefaultHttpContext();
        SimpleMiddleware1 simpleMiddleware1 = new SimpleMiddleware1();

        middlewareChain.addMiddleware(simpleMiddleware1);

        middlewareChain.executeNext(httpContext);

        assertEquals(1, middlewareChain.getCurrent());
        assertTrue(middlewareChain.isFinished());
        assertTrue(simpleMiddleware1.isExecuted());
    }

    @Test
    void execute_With_Two_Middleware(){
        HttpContext httpContext = new DefaultHttpContext();
        SimpleMiddleware1 middleware1 = new SimpleMiddleware1();
        SimpleMiddleware2 middleware2 = new SimpleMiddleware2();

        middlewareChain.addMiddleware(middleware1);
        middlewareChain.addMiddleware(middleware2);

        middlewareChain.executeNext(httpContext);
        assertEquals(2, middlewareChain.getCurrent());
        assertTrue(middlewareChain.isFinished());
        assertTrue(middleware1.isExecuted());
        assertTrue(middleware2.isExecuted());
    }

    @Test
    void execute_With_Stoper_Middleware_in_The_Chain(){
        HttpContext httpContext = new DefaultHttpContext();
        SimpleMiddleware1 middleware1 = new SimpleMiddleware1();
        SimpleMiddleware2 middleware2 = new SimpleMiddleware2();
        StoperMiddleware stoperMiddleware = new StoperMiddleware();

        middlewareChain.addMiddleware(middleware1);
        middlewareChain.addMiddleware(stoperMiddleware);
        middlewareChain.addMiddleware(middleware2);


        middlewareChain.executeNext(httpContext);
        assertEquals(2, middlewareChain.getCurrent());
        assertFalse(middlewareChain.isFinished());
        assertTrue(middleware1.isExecuted());
        assertTrue(stoperMiddleware.isExecuted());
        assertFalse(middleware2.isExecuted());
    }


    @Test
    void execute_With_Thrower_Middleware_in_The_Chain_Must_Throw_a_RuntimeExeption(){
        HttpContext httpContext = new DefaultHttpContext();
        SimpleMiddleware1 middleware1 = new SimpleMiddleware1();
        SimpleMiddleware2 middleware2 = new SimpleMiddleware2();
        ThrowerMiddleware throwerMiddleware = new ThrowerMiddleware();

        middlewareChain.addMiddleware(middleware1);
        middlewareChain.addMiddleware(throwerMiddleware);
        middlewareChain.addMiddleware(middleware2);

        assertThrows(RuntimeException.class, () -> middlewareChain.executeNext(httpContext));
        assertEquals(2, middlewareChain.getCurrent());
        assertFalse(middlewareChain.isFinished());
        assertTrue(middleware1.isExecuted());
        assertFalse(middleware2.isExecuted());
    }


    @Test
    void execute_With_CatcherMiddleware_Must_Catch_RuntimeException(){
        HttpContext httpContext = new DefaultHttpContext();
        SimpleMiddleware1 middleware1 = new SimpleMiddleware1();
        SimpleMiddleware2 middleware2 = new SimpleMiddleware2();
        ThrowerMiddleware throwerMiddleware = new ThrowerMiddleware();
        CatcherMiddleware catcherMiddleware = new CatcherMiddleware();

        middlewareChain.addMiddleware(middleware1);
        middlewareChain.addMiddleware(catcherMiddleware);
        middlewareChain.addMiddleware(throwerMiddleware);
        middlewareChain.addMiddleware(middleware2);

        middlewareChain.executeNext(httpContext);
        assertEquals(3, middlewareChain.getCurrent());
        assertFalse(middlewareChain.isFinished());
        assertTrue(middleware1.isExecuted());
        assertTrue(catcherMiddleware.isExecuted());
        assertFalse(middleware2.isExecuted());
    }

    @Test
    void addMiddleware() {
        SimpleMiddleware1 middleware1 = new SimpleMiddleware1();
        SimpleMiddleware2 middleware2 = new SimpleMiddleware2();

        middlewareChain.addMiddleware(middleware1);
        middlewareChain.addMiddleware(middleware2);

        assertEquals(2, middlewareChain.getMiddlewares().size());
        assertEquals(middleware1, middlewareChain.getMiddlewares().get(0));
        assertEquals(middleware2, middlewareChain.getMiddlewares().get(1));
    }
}

class SimpleMiddleware1 implements IMiddleware{
    private boolean isExecuted = false;
    @Override
    public void execute(MiddlewareChain chain, HttpContext httpContext) {
        isExecuted = true;
        chain.executeNext(httpContext);
    }

    public boolean isExecuted() {
        return isExecuted;
    }
}

class SimpleMiddleware2 implements IMiddleware{

    private boolean isExecuted = false;

    @Override
    public void execute(MiddlewareChain chain, HttpContext httpContext) {
        isExecuted = true;
    }
    boolean isExecuted() {
        return isExecuted;
    }
}

class StoperMiddleware implements IMiddleware{
    private boolean isExecuted = false;

    public void execute(MiddlewareChain chain, HttpContext httpContext) {
        isExecuted = true;
    }
    boolean isExecuted() {
        return isExecuted;
    }
}

class ThrowerMiddleware implements IMiddleware{
    public void execute(MiddlewareChain chain, HttpContext httpContext) {
        throw new RuntimeException("The middleware throw an exception");
    }
}

class CatcherMiddleware implements IMiddleware{

    private boolean isExecuted;

    public void execute(MiddlewareChain chain, HttpContext httpContext) {
        try {
            isExecuted = true;
            chain.executeNext(httpContext);
        }catch (Exception ignore){}
    }
    boolean isExecuted() {
        return isExecuted;
    }
}