package Everest.Core;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;

import static java.lang.annotation.ElementType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationsTest {

    @Test
    void annotatedAnnotation() {
    }

    @Test
    void annotatedAnnotation1() {
    }

    @Test
    void annotatedAnnotations_WithNonAnnotatedParameter() {
        Method method0 = Reflexions.getMethod(TestClass.class, "OneArgumentMethod", int.class);

        List<Annotation> annotations =Annotations.annotatedAnnotations(method0, AnnotationAnnotator.class);
        assertEquals(0, annotations.size());
    }

    @Test
    void annotatedAnnotations_WithOneAnnotatedAnnotationParameter() {
        Method method = Reflexions.getMethod(TestClass.class, "method1WithOneAnnotatedAnnotation", int.class);

        List<Annotation> annotations =Annotations.annotatedAnnotations(method, AnnotationAnnotator.class);
        assertEquals(1, annotations.size());
    }

    @Test
    void annotatedAnnotations_WithTwoAnnotatedAnnotationParameter() {
        Method method = Reflexions.getMethod(TestClass.class, "method1WithTwoAnnotatedAnnotation", int.class);
        List<Annotation> annotations = Annotations.annotatedAnnotations(method, AnnotationAnnotator.class);
        assertEquals(2, annotations.size());
    }

    @Target(ANNOTATION_TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    private  @interface AnnotationAnnotator {
    }

    @AnnotationAnnotator
    @Target({METHOD, PARAMETER, CONSTRUCTOR, TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface AnnotatedAnnotation1{}

    @AnnotationAnnotator
    @Target({METHOD, PARAMETER, CONSTRUCTOR, TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface AnnotatedAnnotation2{}


    @Target({METHOD, PARAMETER, CONSTRUCTOR, TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface SimpleAnnotation{}

    private class TestClass{
        public void method0(){}
        public void methodWithoutAnnotation(int param1){}

        @AnnotatedAnnotation1
        public void method1WithOneAnnotatedAnnotation(int param1){}

        @AnnotatedAnnotation1 @AnnotatedAnnotation2
        public void method1WithTwoAnnotatedAnnotation( int param1){}
    }
}