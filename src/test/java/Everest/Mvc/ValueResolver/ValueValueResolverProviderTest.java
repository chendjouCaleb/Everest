package Everest.Mvc.ValueResolver;

import Everest.Core.Exception.InvalidOperationException;
import Everest.Http.HttpContext;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

import static org.junit.jupiter.api.Assertions.*;

class ValueValueResolverProviderTest {
    private ValueResolverProvider provider = new ValueResolverProvider();

    @Test
    void addTypeResolver() {
        SimpleTypeResolver1 resolver1 = new SimpleTypeResolver1();
        provider.addResolver(resolver1);

        assertEquals(1, provider.getTypedResolvers().size());
        assertEquals(resolver1, provider.getTypedResolver(SimpleClass1.class));
    }

    @Test
    void addTypeResolver_WithoutGenericsArguments(){
        assertThrows(InvalidOperationException.class, () -> provider.addResolver(new TypeResolverWithoutArgument()));
        assertEquals(0, provider.getTypedResolvers().size());
    }

    @Test
    void addAnnotationResolver() {
        SimpleAnnotationResolver resolver = new SimpleAnnotationResolver();
        provider.addResolver(resolver);

        assertEquals(1, provider.getAnnotationResolvers().size());
        assertEquals(resolver, provider.getAnnotationResolver(SimpleAnnotation.class));
    }

    @Test
    void addAnnotationResolver_WithoutGenericsArguments(){
        assertThrows(InvalidOperationException.class, () -> provider.addResolver(new AnnotationResolverWithoutArgument()));
        assertEquals(0, provider.getAnnotationResolvers().size());
    }

    private class SimpleClass1{}
    private @interface SimpleAnnotation{}
    private class SimpleTypeResolver1 implements ITypedValueResolver<SimpleClass1>{
        public SimpleClass1 getValue(HttpContext httpContext, Parameter parameter) {
            return null;
        }
    }
    private class TypeResolverWithoutArgument implements ITypedValueResolver{
        public Object getValue(HttpContext httpContext, Parameter parameter) {
            return null;
        }
    }

    private class SimpleAnnotationResolver implements IAnnotationValueResolver<SimpleAnnotation>{
        public Object getVariable(HttpContext httpContext, Parameter parameter, SimpleAnnotation annotation) {
            return null;
        }
    }

    private class AnnotationResolverWithoutArgument implements IAnnotationValueResolver{
        public Object getVariable(HttpContext httpContext, Parameter parameter, Annotation annotation) {
            return null;
        }
    }

}