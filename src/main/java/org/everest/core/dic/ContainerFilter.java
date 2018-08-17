package org.everest.core.dic;

import org.everest.core.dic.handler.ITypeFilter;
import org.everest.utils.Assert;
import org.everest.utils.ReflexionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe sera chargée de prendre une liste de classe, et de la filtré pour extraire
 * les classes et les méthodes qui seront candidates à
 * l'injection de dépendances.
 * @author chendjou deGracce
 * @version 1.0
 */
public class ContainerFilter {
    private Logger logger = LoggerFactory.getLogger(ContainerFilter.class);
    private List<Class<? extends Annotation>> typeAnnotations = new ArrayList<>();
    private List<Class<? extends Annotation>> methodAnnotations = new ArrayList<>();
    private List<ITypeFilter> typeFilters = new ArrayList<>();
    private List<Class> classForFilter = new ArrayList<>();

    private List<Method> foundedMethod = new ArrayList<>();
    private List<Class> annotatedFoundedClass = new ArrayList<>();
    private List<Class> filteredFoundedClass = new ArrayList<>();
    private List<Class> independentClass = new ArrayList<>();
    private List<Class> methodFoundedClass = new ArrayList<>();

    void doMethodFilter(){
     for (Class type: classForFilter){
         for (Method method:type.getDeclaredMethods()){
             if(ReflexionUtils.isAnnotatedBy(method, methodAnnotations)){
                 Assert.isFalse(isPresent(method.getReturnType()),
                         "Le class '" + method.getReturnType() + "' à déjà été listé pour une candidature.");
                 methodFoundedClass.add(method.getReturnType());
                 foundedMethod.add(method);
             }
         }
     }
    }

    public void doClassFilter(){
        doAnnotationFilter();
        doMethodFilter();
        doTypeFilterFilter();

    }

    void doAnnotationFilter(){
        classForFilter.forEach(type -> {
            if (ReflexionUtils.isAnnotatedBy(type, typeAnnotations)) {
                Assert.isFalse(type.isInterface(), type.getName() + " is not admissible for instanciation");
                Assert.isFalse(isPresent(type), "La class '" + type + "' à déjà été listé pour une candidature.");
                annotatedFoundedClass.add(type);
            }
        });
    }



    void doTypeFilterFilter(){
        for (Class type: classForFilter){
            for (ITypeFilter typeFilter:typeFilters){
                if(typeFilter.isAdmissible(type)){
                    Assert.isFalse(isPresent(type), "Le class '" + type + "' à déjà été listé pour une candidature.");
                    filteredFoundedClass.add(type);
                }
            }
        }
    }

    public void addClass(Class type){
        Assert.isFalse(isPresent(type), "Le class '" + type + "' à déjà été listé pour une candidature.");
        independentClass.add(type);
    }

    private boolean isPresent(Class type) {
        return annotatedFoundedClass.contains(type) || filteredFoundedClass.contains(type) || methodFoundedClass.contains(type);
    }

    public List<Class> getCandidates() {
        List<Class> candidates = new ArrayList<>();
        candidates.addAll(methodFoundedClass);
        candidates.addAll(annotatedFoundedClass);
        candidates.addAll(filteredFoundedClass);
        candidates.addAll(independentClass);
        return candidates;
    }


    public List<Class<? extends Annotation>> getTypeAnnotations() {
        return typeAnnotations;
    }

    public void addTypeAnnotation(Class<? extends Annotation> typeAnnotation) {
        this.typeAnnotations.add(typeAnnotation);
    }

    public List<Class<? extends Annotation>> getMethodAnnotations() {
        return methodAnnotations;
    }

    public void addMethodAnnotation(Class<? extends Annotation> methodAnnotation) {
        this.methodAnnotations.add(methodAnnotation);
    }

    public List<ITypeFilter> getTypeFilters() {
        return typeFilters;
    }

    public void addTypeFilters(ITypeFilter typeFilter) {
        this.typeFilters.add(typeFilter);
    }

    public List<Class> getClassForFilter() {
        return classForFilter;
    }

    public void setClassForFilter(List<Class> classForFilter) {
        this.classForFilter = classForFilter;
    }

    public List<Method> getFoundedMethod() {
        return foundedMethod;
    }




    public List<Class> getAnnotatedFoundedClass() {
        return annotatedFoundedClass;
    }

    public List<Class> getFilteredFoundedClass() {
        return filteredFoundedClass;
    }

    public List<Class> getMethodFoundedClass() {
        return methodFoundedClass;
    }

    public List<Class> getIndependentClass() {
        return independentClass;
    }

    public void setTypeAnnotations(List<Class<? extends Annotation>> typeAnnotations) {
        this.typeAnnotations = typeAnnotations;
    }

    public void setMethodAnnotations(List<Class<? extends Annotation>> methodAnnotations) {
        this.methodAnnotations = methodAnnotations;
    }

    public void setTypeFilters(List<ITypeFilter> typeFilters) {
        this.typeFilters = typeFilters;
    }

    public void summarize(){
        logger.info("There are " + classForFilter.size() + " to filter");
        logger.info("There are {} annotations for classes", typeAnnotations);
        logger.info("There are {} annotations for methods", methodAnnotations);
        logger.info("There are {} filter for classes", typeFilters);
        logger.info("{} classes was founded by Annotations", annotatedFoundedClass.size());
        logger.info("{} classes was founded by Filter", filteredFoundedClass.size());
        logger.info("{} method was founded", methodFoundedClass.size());
    }
}
