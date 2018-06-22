package org.everest.mvc.filter;

import org.everest.context.ApplicationContext;
import org.everest.mvc.classHandler.RequestFilterHandler;
import org.everest.mvc.controller.FirstController;
import org.everest.mvc.filter.method.FilterFourMethod;
import org.everest.mvc.filter.method.FilterOneMethod;
import org.everest.mvc.filter.method.FilterThreeMethod;
import org.everest.mvc.filter.method.FilterTwoMethod;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;
import org.everest.mvc.result.IFilterResult;
import org.everest.mvc.result.RouteRedirection;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.RouterUtilsTest;
import org.everest.utils.ReflexionUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

public class FilterManagerTest {
    private Logger logger = LoggerFactory.getLogger(RouterUtilsTest.class);
    private FilterManager filterManager;
    private ApplicationContext applicationContext = new ApplicationContext();
    HttpContext httpContext;

    @Before
    public void setUp() throws Exception {
        applicationContext.addClassHandler(new RequestFilterHandler());
        applicationContext.addByPackage("org.everest.mvc");
        applicationContext.initialize();
        filterManager = applicationContext.getInstance(FilterManager.class);

        httpContext = new HttpContext();
        httpContext.setController(new FirstController());
        httpContext.setRoute(new Route());
        httpContext.getRoute().setMethod(ReflexionUtils.getMethod(FirstController.class, "firstAction"));
    }

    @Test
    public void getFilters() throws Exception {
        Method method = ReflexionUtils.getMethod(FirstController.class, "firstAction");
        List<IFilter> filters = filterManager.getFilters(FirstController.class, method);

        assertEquals(1, filters.size());
        logger.info("IFilter: {} ",filters.get(0).getClass().getName());
    }

    @Test
    public void getFilterChain(){

        FilterChain filterChain = filterManager.getFilterChain(httpContext);

        assertEquals(1, filterChain.getFilters().size());
        assertEquals(httpContext.getFilterChain(), filterChain);
    }

    @Test
    public void executeFilter(){
        IFilter filter = new FilterOneMethod();
        httpContext.setRequest(new Request(null));
        httpContext.setResponse(new Response(null));

        IFilterResult filterResult = filterManager.executeFilter(filter, httpContext);
        logger.info("Result " + filterResult.getClass());
    }

    @Test
    public void getFilterMethod_With_Name(){
        Method method = filterManager.getFilterMethod(FilterOneMethod.class);
        assertEquals(method.getName(), "execute");
    }

    @Test
    public void getFilterMethod_With_Annotation(){
        Method method = filterManager.getFilterMethod(FilterTwoMethod.class);
        assertEquals(method.getName(), "executeFilter");
    }

    @Test public void executeFilterChain(){
        FilterChain filterChain = new FilterChain();
        FilterTwoMethod filterTwo = new FilterTwoMethod();
        filterChain.addFilter(new FilterOneMethod());
        filterChain.addFilter(filterTwo);

        filterManager.executeFilterChain(filterChain, httpContext);
        assertTrue(filterChain.isFinished());
        assertEquals(filterChain.getCurrentFilter(), filterTwo);
    }

    @Test public void executeFilterChain_With_Response_Sender_Result(){
        FilterThreeMethod filterThree = new FilterThreeMethod();
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new FilterOneMethod());
        filterChain.addFilter(new FilterTwoMethod());
        filterChain.addFilter(filterThree);
        filterChain.addFilter(new FilterFourMethod());

        filterManager.executeFilterChain(filterChain, httpContext);
        assertFalse(filterChain.isFinished());
        assertEquals(filterChain.getCurrentFilter(), filterThree);
        assertEquals(httpContext.getActionResult().getClass(), RouteRedirection.class);
    }

}