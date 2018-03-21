package org.everest.core.dic.service;

import dic.AutoInject;
import org.everest.core.dic.DicUtils;
import org.everest.core.dic.Instance;
import org.everest.core.dic.contract.IRetrieverService;
import org.everest.core.dic.contract.IDependencyResolver;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.core.dic.exception.DependencyResolutionException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class IDependencyResolverImpl implements IDependencyResolver {

    @Override
    public Instance createInstance() {
        return null;
    }

    @Override
    public void resolveInjection(IRetrieverService retrieverService, Instance instance,
                                 Map<String, Instance> instances, Collection<String> dependencyKeys) {
        Field[] fields = instance.getType().getDeclaredFields();
        for (Field field: fields){
            AutoWired autoWired = field.getAnnotation(AutoWired.class);
            AutoInject autoInject = field.getAnnotation(AutoInject.class);
            try{
                if(autoWired != null){
                    field.setAccessible(true);
                    if(autoWired.qualifier().equals("")){
                        DicUtils.assignField(field,instance.getInstance(), retrieverService.getInstance(field.getType(), instances).getInstance());
                    }else {
                        DicUtils.assignField(field, instance.getInstance(), retrieverService.getInstance(autoWired.qualifier(), instances).getInstance());
                    }
                }else if(autoInject != null){
                    field.setAccessible(true);
                    DicUtils.assignField(field,instance.getInstance(), retrieverService.getInstance(field.getType(), instances).getInstance());
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error occured during the resolving the dependencies of "
                        + instance.getType().getSimpleName());
                throw new DependencyResolutionException(e);
            }



        }
    }


    @Override
    public void resolveMethodInjection(Instance instance, List<String> dependencyKeys) {

    }

    @Override
    public void resolveContructorInjection(Instance instance, List<String> dependencyKeys) {

    }
}
