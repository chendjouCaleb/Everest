package org.everest.mvc.filter;

import org.everest.context.ApplicationContext;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.decorator.Instance;
import org.everest.mvc.FilterExecutionException;
import org.everest.mvc.actionResultHandler.ActionResultHandler;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.httpContext.decorator.FilterBy;
import org.everest.mvc.httpContext.decorator.FilterMethod;
import org.everest.mvc.result.ActionResult;
import org.everest.mvc.result.IFilterResult;
import org.everest.mvc.result.Next;
import org.everest.mvc.variableResolver.RequestVariableResolver;
import org.everest.utils.ReflexionUtils;
import org.everest.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Instance
public class FilterManager {
    private Logger logger = LoggerFactory.getLogger(FilterManager.class);
    @AutoWired private ApplicationContext applicationContext;
    @AutoWired private RequestVariableResolver variableResolver;
    @AutoWired private ActionResultHandler resultHandler;

    public void handleFilter(HttpContext context) {
        FilterChain filterChain = getFilterChain(context);
        executeFilterChain(filterChain, context);
    }

    void executeFilterChain(FilterChain filterChain, HttpContext httpContext){
        List<IFilter> filters = filterChain.getFilters();
        for (IFilter filter:filters){
            filterChain.setCurrentFilter(filter);
            IFilterResult result = executeFilter(filter, httpContext);
            logger.info("\nFilter execution =[{}]", filter.getClass());
            logger.info("Filter Result Class= [{}]", result.getClass());
            if (!result.getClass().equals(Next.class)){
                httpContext.setActionResult((ActionResult)result);
                logger.info("Filter execution Intercepted");
                return;
            }
        }
        filterChain.setFinished(true);
        logger.info("Filer chain executed successful");
    }

    IFilterResult executeFilter(IFilter filter, HttpContext httpContext) {
        Method method = getFilterMethod(filter.getClass());

        try {
            Object[] params = variableResolver.getVariables(method, httpContext);
            Object result = Utils.callRemote(filter, method, params);
            return (IFilterResult) result;
        } catch (Exception e) {
            String message = "Erreur durant l'exécution du filtre: " + filter.getClass().getName();
            logger.error(message, e);
            throw new FilterExecutionException(message, e);
        }
    }

    Method getFilterMethod(Class<? extends IFilter> filterClass) {
        Method method = ReflexionUtils.findMethodByAnnotation(filterClass, FilterMethod.class);
        if(method != null){
            method =  ReflexionUtils.findMethodByAnnotation(filterClass, FilterMethod.class);
        }else if(ReflexionUtils.getMethod(filterClass, "execute") != null){
            method =  ReflexionUtils.getMethod(filterClass, "execute");
        }
        if (method == null){
            throw new FilterExecutionException("La classe de filtre: " + filterClass.getName() +
                    " ne possède aucune méthode de filtre.");
        }else if (!ReflexionUtils.isImplement(method.getReturnType(), IFilterResult.class)){
            throw new FilterExecutionException("La méthode '" + method.getName() + "' du filtre '"
                    + filterClass.getName() + "' doit retourner un IfilterResult");
        }
        return method;
    }

    FilterChain getFilterChain(HttpContext context) {
        FilterChain filterChain = new FilterChain();
        List<IFilter>filters = getFilters(context.getController().getClass(), context.getMethod());
        logger.info("Filters: {}", filters.size());
        filterChain.setFilters(filters);
        context.setFilterChain(filterChain);
        return filterChain;
    }

    List<IFilter> getFilters(Class controller, Method method) {
        List<IFilter> filters = new ArrayList<>();
        List<Annotation> annotations = new ArrayList<>();
        annotations.addAll(Arrays.asList(controller.getAnnotations()));
        annotations.addAll(Arrays.asList(method.getAnnotations()));
        for (Annotation annotation : annotations) {
            FilterBy filter = annotation.annotationType().getAnnotation(FilterBy.class);
            if (filter != null) {
                try {
                    IFilter instance = (IFilter) applicationContext.getInstance(filter.filter());
                    instance.init(annotation);
                    filters.add(instance);
                } catch (Exception e) {
                    throw new FilterExecutionException(e);
                }
            }
        }
        return filters;
    }

}
