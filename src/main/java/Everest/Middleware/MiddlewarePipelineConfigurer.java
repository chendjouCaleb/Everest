package Everest.Middleware;

import java.util.Collection;


public class MiddlewarePipelineConfigurer {
    private MiddlewarePipeline pipeline;

    public MiddlewarePipelineConfigurer(MiddlewarePipeline pipeline) {
        this.pipeline = pipeline;
    }

    public void configure(MiddlewareRegister register, Collection<IMiddleware> middlewares){
        register.getMiddlewares().forEach(type -> {
            IMiddleware middleware = middlewares.stream().filter(m -> m.getClass().equals(type))
                    .findFirst().orElseThrow(() ->
                            new IllegalArgumentException("The instance of class " + type + " is not present in middleware collection"));
            pipeline.addMiddleware(middleware);
        });
    }
}
