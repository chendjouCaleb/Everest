package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import org.everest.decorator.Repository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;

public class RepositoryClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {
        Annotation repository = type.getAnnotation(Repository.class);
        if(repository != null){
            return false;
        }
        if(type.isInterface()){
            return false;
        }
        if(Modifier.isAbstract(type.getModifiers())){
            return false;
        }

        return type.getName().endsWith("Repository");
    }
}
