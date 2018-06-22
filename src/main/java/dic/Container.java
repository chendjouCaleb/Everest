package dic;

import dic.exception.ServiceInstantiationException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class Container {
    private static Container instance = new Container();
    private Map<Class, Object> serviceInstances = new HashMap<>();

    private Container() {
    }

    public static Container getContainer() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public Object get(Class serviceClass) {
        if (hasService(serviceClass)) {
            return serviceInstances.get(serviceClass);
        }
        Object service = createServiceInstance(serviceClass);
        addServiceInstance(service);
        return service;
    }

    private Object get(Class target, boolean b) {
        if (b) {
            return createServiceInstance(target);
        } else {
            return get(target);
        }

    }

    public static Object getService(Class serviceClass) {
        return getContainer().get(serviceClass);
    }

    private boolean hasService(Class serviceClass) {
        return serviceInstances.get(serviceClass) != null;
    }

    private Object createServiceInstance(Class serviceClass) {
        Object service = null;
        try {
            service = serviceClass.newInstance();
            System.out.println("New instantiation of " + serviceClass);
            instanciateMember(service);
            executeInit(service);
        } catch (Exception e) {
            try {
                throw new ServiceInstantiationException();
            } catch (ServiceInstantiationException e1) {
                e.printStackTrace();
                e1.printStackTrace();
            }
        }
        return service;
    }

    private void executeInit(Object object) throws Exception {
        List<Method> methods = new ArrayList<>();
        methods.addAll(Arrays.asList(object.getClass().getDeclaredMethods()));


        for (Class clazz : object.getClass().getInterfaces()) {
            methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        }


        for (Method method : methods) {
            OnInit init = method.getAnnotation(OnInit.class);
            if (init != null) {
                method.setAccessible(true);
                method.invoke(object);
                //Utils.callRemote(object, method.getName());
                System.out.println("[Initiation][Class: " + object.getClass() + "]  [Method: " + method.getName() + "]");

            }
        }
    }

    public void addServiceInstance(Object service) {
        if (!serviceInstances.containsKey(service.getClass())) {
            serviceInstances.put(service.getClass(), service);
        }
    }


    public Map<Class, Object> getServiceInstances() {
        return serviceInstances;
    }

    private void instanciateMember(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            AutoInject autoInject = field.getAnnotation(AutoInject.class);
            Object instance = null;
            if (autoInject != null) {
                field.setAccessible(true);
                if (autoInject.target() != Object.class) {
                    System.out.println("Target class : " + autoInject.target());
                    instance = get(autoInject.target(), autoInject.newInstance());
                } else {
                    Injectable injectable = field.getType().getAnnotation(Injectable.class);
                    if (injectable != null && !injectable.target().equals(void.class)) {
                        instance = get(injectable.target());
                        System.out.println("INJECTABLE TARGET: " + injectable.target());
                    } else {
                        instance = get(field.getType());
                    }

                }
                try {
                    System.out.println("New member instantiation of " + field.getType());
                    field.set(object, instance);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void instanceClassOfPackage(String packagesName[]) {

    }
}
