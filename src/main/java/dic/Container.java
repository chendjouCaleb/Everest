package dic;

import dic.exception.ServiceInstantiationException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Container {
    private static Container instance = new Container();
    private Map<Class, Object> serviceInstances = new HashMap<>();

    private Container(){}

    public static Container getContainer(){
        if (instance == null){
            instance = new Container();
        }
        return instance;
    }

    public Object get(Class serviceClass){
        if(hasService(serviceClass)){
            return serviceInstances.get(serviceClass);
        }
        Object service = createServiceInstance(serviceClass);
        addServiceInstance(service);
        return service;
    }
    public static Object getService(Class serviceClass){
        return getContainer().get(serviceClass);
    }
    private boolean hasService(Class serviceClass){
        return serviceInstances.get(serviceClass) != null;
    }

    private Object createServiceInstance(Class serviceClass){
        Object service =null;
        try {
            service = serviceClass.newInstance();
            System.out.println("New instantiation of " + serviceClass);
            instanciateMember(service);
        } catch (Exception e) {
            try {
                throw new ServiceInstantiationException();
            } catch (ServiceInstantiationException e1) {
                e1.printStackTrace();
            }
        }
        return service;
    }

    public void addServiceInstance(Object service){
        if(!serviceInstances.containsKey(service.getClass())){
            serviceInstances.put(service.getClass(), service);
        }
    }


    public Map<Class, Object> getServiceInstances() {
        return serviceInstances;
    }

    private void instanciateMember(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field: fields){
            AutoInject autoInject = field.getAnnotation(AutoInject.class);
            if(autoInject != null){
                Object instance = get(field.getType());
                try {
                    System.out.println("New member instantiation of " + field.getType());
                    field.set(object, instance);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void instanceClassOfPackage(String packagesName[]){

    }
}
