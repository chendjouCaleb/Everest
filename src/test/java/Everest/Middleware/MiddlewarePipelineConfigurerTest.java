package Everest.Middleware;

import Everest.Http.HttpContext;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MiddlewarePipelineConfigurerTest {

    @Test
    void configure() {

        MiddlewarePipeline pipeline = new MiddlewarePipeline();
        MiddlewareRegister register = new MiddlewareRegister();
        register.use(SimpleMiddleware1.class);
        register.use(SimpleMiddleware2.class);

        MiddlewarePipelineConfigurer configurer = new MiddlewarePipelineConfigurer(pipeline);

        List<IMiddleware> middlewares = new ArrayList<>();
        SimpleMiddleware1 middleware1 = new SimpleMiddleware1();
        SimpleMiddleware2 middleware2 = new SimpleMiddleware2();
        middlewares.add(middleware1);
        middlewares.add(middleware2);

        configurer.configure(register, middlewares);

        assertEquals(2, pipeline.getMiddlewares().size());
        assertEquals(middleware1, pipeline.getMiddlewares().get(0));
        assertEquals(middleware2, pipeline.getMiddlewares().get(1));
    }

    @Test
    void configure_With_No_Instance_Middleware() {

        MiddlewarePipeline pipeline = new MiddlewarePipeline();
        MiddlewareRegister register = new MiddlewareRegister();
        register.use(SimpleMiddleware1.class);
        register.use(SimpleMiddleware2.class);

        MiddlewarePipelineConfigurer configurer = new MiddlewarePipelineConfigurer(pipeline);

        List<IMiddleware> middlewares = new ArrayList<>();
        SimpleMiddleware1 middleware1 = new SimpleMiddleware1();
        middlewares.add(middleware1);

        assertThrows(IllegalArgumentException.class, () -> configurer.configure(register, middlewares));
    }

    class SimpleMiddleware1 implements IMiddleware{
        public void execute(MiddlewareChain chain, HttpContext httpContext) { }
    }
    class SimpleMiddleware2 implements IMiddleware{
        public void execute(MiddlewareChain chain, HttpContext httpContext) { }
    }
    class SimpleMiddleware3 implements IMiddleware{
        public void execute(MiddlewareChain chain, HttpContext httpContext) { }
    }
}

