package org.everest.core.dic.service;

import org.apache.commons.beanutils.ConstructorUtils;
import org.everest.core.dic.FactoryInstance;
import org.everest.core.dic.Instance;
import org.everest.core.dic.TypeInstance;
import org.everest.core.dic.contract.IDependencyResolver;
import org.everest.core.dic.contract.IRetrieverService;
import org.everest.core.dic.exception.InstanceException;
import org.everest.utils.ReflexionUtils;
import org.everest.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class IDependencyResolverImpl implements IDependencyResolver {
    Logger logger = LoggerFactory.getLogger(IDependencyResolverImpl.class);
    private IRetrieverService retrieverService;
    private List<Instance> instances;

    public IDependencyResolverImpl(IRetrieverService retrieverService) {
        this.retrieverService = retrieverService;
        this.instances = retrieverService.getInstances();
    }

    public void setParentToFactoryInstance(){
        List<FactoryInstance> factoryInstances = retrieverService.getFactoryInstance();
        factoryInstances.forEach(factoryInstance ->
                factoryInstance.setParentInstance(retrieverService.getInstance(factoryInstance.getMethod().getDeclaringClass())));
    }

    @Override
    public void resolveInstance(){
        while (!isComplete()){
            for (Instance instance:instances){
                if(instance.getInstance() == null && isResolvable(instance)){

                    resolveInstance(instance);
                    logger.info("Resolution of: [{}]", instance.getRegisteredType(), instance.getInstance().getClass());
                }
            }
        }
    }

    private boolean isResolvable(Instance instance){
        List<Instance> dependentInstances = retrieverService.getInstances(instance.getDependencies());
        logger.info("Test of: {}", instance.getRegisteredType());
        for (Instance instance1: dependentInstances){

            logger.info("\t instance dependances: {}", instance1.getRegisteredType());
            if(instance1.getInstance() == null){
                return false;
            }
        }
        logger.info("Instance of ok!");
        return true;
    }

    private boolean isComplete(){
        for (Instance instance:instances){
            if(instance.getInstance() == null ){
                return false;
            }
        }
        return true;
    }

    public void resolveInstance(Instance instance){
        if(instance.getClass().equals(TypeInstance.class)){
            TypeInstance typeInstance = (TypeInstance) instance;
            Object[] constructorObjects = retrieverService.getObjects(retrieverService.getInstances(typeInstance.getConstructorDependencies())).toArray();
            resolveConstructor(typeInstance, constructorObjects);
            resolveFields(typeInstance);
            executePostInitialization(typeInstance);
        }else {
            FactoryInstance factoryInstance = (FactoryInstance) instance;
            resolveFactory(factoryInstance);
        }
    }

    public void executePostInitialization(TypeInstance instance){
        try {
            Method method = instance.getConcreteType().getMethod("afterInitialization");
            try {
                method.invoke(instance.getInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException ignore) { }
    }
    public void resolveConstructor(TypeInstance instance, Object[] objects){
        try {
            Object obj = ConstructorUtils.invokeConstructor(instance.getConcreteType(), objects);
            instance.setInstance(obj);
        } catch (Exception e) {
            throw new InstanceException("Erreur lors de la création de l'instance '" + instance.getRegisteredType() + "'",e);
        }
    }

    public void resolveFactory(FactoryInstance instance){
        Object[] objects = retrieverService.getObjectByRegisteredType(instance.getMethodDependencies()).toArray();
        try {
            //instance.getMethod().setAccessible(true);
           Object obj = Utils.callRemote(instance.getParentInstance().getInstance(), instance.getMethod(), objects);
            //Object obj = MethodUtils.invokeMethod(instance.getParentInstance().getInstance(), instance.getMethod(), objects);
            instance.setInstance(obj);
            instance.setConcreteType(obj.getClass());
        } catch (Exception e) {
            e.printStackTrace();
            throw new InstanceException("Erreur lors de la création de l'instance par méthode '" + instance.getMethod() + "'",e);
        }
    }

    public void resolveFields(TypeInstance instance){
        List<Field> fields = ReflexionUtils.getAllFields(instance.getConcreteType());
        for (Field field: fields){
            if(instance.getFieldDependencies().contains(field.getType())){
                field.setAccessible(true);
                try {
                    field.set(instance.getInstance(), retrieverService.getInstance(field.getType()).getInstance());
                } catch (IllegalAccessException e) {
                    throw new InstanceException("Erreur lors de la resolution du champ " +
                            field.getName() + " de la classe " + instance.getConcreteType(), e);
                }
            }
        }

    }



}
