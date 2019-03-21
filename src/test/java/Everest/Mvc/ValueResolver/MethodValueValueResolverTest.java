package Everest.Mvc.ValueResolver;

import Everest.Core.Reflexions;
import Everest.Http.DefaultHttpContext;
import Everest.Http.HttpContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static java.lang.annotation.ElementType.*;
import static org.junit.jupiter.api.Assertions.*;

class MethodValueValueResolverTest {
    private MethodValueResolver valueResolver;
    private ValueResolverProvider provider;

    @BeforeEach
    void setUp() {
        provider = new ValueResolverProvider();
        provider.addResolver(new AnnotatedAnnotationValueResolver1());
        provider.addResolver(new AnnotatedAnnotationValueResolver2());
        provider.addResolver(new IntegerValueResolver());

        valueResolver = new MethodValueResolver(provider);
    }

    @Test
    void resolveZeroArgumentMethod(){
        Method method = Reflexions.getMethod(TestClass.class, "method");
        Object[] values = valueResolver.getVariables(method, new DefaultHttpContext());
        assertEquals(0, values.length);
    }

    @Test
    void resolveOneTypeArgumentMethod(){
        Method method = Reflexions.getMethod(TestClass.class,"OneArgumentMethod", Integer.class);
        Object[] values = valueResolver.getVariables(method, new DefaultHttpContext());
        assertEquals(1, values.length);
        assertEquals(0, values[0]);
    }


    @Test
    void resolveTwoTypeArgumentMethod(){
        Method method = Reflexions.getMethod(TestClass.class,"TwoArgumentMethod", Integer.class, Integer.class);
        Object[] values = valueResolver.getVariables(method, new DefaultHttpContext());
        assertEquals(2, values.length);
        assertEquals(0, values[0]);
        assertEquals(0, values[1]);
    }

    @Test
    void resolveOneTypeArgumentMethod_WithoutTypeValueResolver(){
        Method method = Reflexions.getMethod(TestClass.class,"OneArgumentMethod", String.class);
        assertThrows(ValueResolverException.class, () -> valueResolver.getVariables(method, new DefaultHttpContext()));
    }

    @Test
    void resolveOneAnnotatedArgumentMethod(){
        Method method = Reflexions.getMethod(TestClass.class,"OneArgumentMethodWithOneAnnotation", int.class);
        Object[] values = valueResolver.getVariables(method, new DefaultHttpContext());
        assertEquals(1, values.length);
        assertEquals(100, values[0]);
    }

    @Test
    void resolveOneArgumentMethod_WithTwoAnnotation(){
        Method method = Reflexions.getMethod(TestClass.class,"OneArgumentMethodWithTwoAnnotation", int.class);
        assertThrows(ValueResolverException.class, () -> valueResolver.getVariables(method, new DefaultHttpContext()));
    }

    @Test
    void resolveOneArgumentMethod_WithNoResolverAnnotation(){
        Method method = Reflexions.getMethod(TestClass.class,"OneArgumentMethodWithTwoNoResolverAnnotation", int.class);
        assertThrows(ValueResolverException.class, () -> valueResolver.getVariables(method, new DefaultHttpContext()));
    }

    @Test
    void resolveTwoArgumentMethod_WithOneTypeArgument_And_OneAnnotation(){
        Method method = Reflexions.getMethod(TestClass.class,"TwoArgumentMethodWithOneTypeAndOneAnnotation", Integer.class, Integer.class);
        Object[] values = valueResolver.getVariables(method, new DefaultHttpContext());
        assertEquals(2, values.length);
        assertEquals(100, values[0]);
        assertEquals(0, values[1]);
    }

    @ValueResolver()
    @Target(PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface AnnotatedAnnotation1{
        int value();
    }

    @ValueResolver()
    @Target(PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    private @interface AnnotatedAnnotation2{
        int value();
    }


    @Target(PARAMETER) @ValueResolver
    @Retention(RetentionPolicy.RUNTIME)
    private @interface AnnotationWithoutResolver{}

    private class TestClass{
        public void method(){}
        public void OneArgumentMethod(Integer param1){}
        public void TwoArgumentMethod(Integer param1, Integer param2){}
        public void OneArgumentMethod(String param1){}


        public void OneArgumentMethodWithOneAnnotation(@AnnotatedAnnotation1(100) int param1){}

        public void OneArgumentMethodWithTwoAnnotation(@AnnotatedAnnotation1(1) @AnnotatedAnnotation2(2) int param1){}

        public void OneArgumentMethodWithTwoNoResolverAnnotation(@AnnotationWithoutResolver int param){}

        public void TwoArgumentMethodWithOneTypeAndOneAnnotation(@AnnotatedAnnotation1(100) Integer param1, Integer param2){}
    }

    private class AnnotatedAnnotationValueResolver1 implements IAnnotationValueResolver<AnnotatedAnnotation1>{

        @Override
        public Object getVariable(HttpContext httpContext, Parameter parameter, AnnotatedAnnotation1 annotation) {
            return annotation.value();
        }
    }

    private class AnnotatedAnnotationValueResolver2 implements IAnnotationValueResolver<AnnotatedAnnotation1>{

        @Override
        public Object getVariable(HttpContext httpContext, Parameter parameter, AnnotatedAnnotation1 annotation) {
            return annotation.value();
        }
    }

    private class IntegerValueResolver implements ITypedValueResolver<Integer>{
        public Integer getValue(HttpContext httpContext, Parameter parameter) {
            return 0;
        }
    }



}