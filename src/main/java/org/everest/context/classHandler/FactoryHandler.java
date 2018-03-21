package org.everest.context.classHandler;

import org.everest.context.ApplicationContext;
import org.everest.core.dic.DicUtils;
import org.everest.core.dic.decorator.Bean;
import org.everest.decorator.Factory;

import java.lang.reflect.Method;

public class FactoryHandler implements ClassHandler<Factory> {
    @Override
    public void handleClass(Class<?> clazz, Factory factory, ApplicationContext context) {
        context.addInstance(clazz);
        Object obj = context.getInstance(clazz);

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            Bean bean = method.getAnnotation(Bean.class);
            if (bean != null) {
                Object object = DicUtils.callRemote(obj, method.getName());

                if (bean.value().equals("")) {
                    context.addInstance(object);
                }else {
                    context.addInstance(object, bean.value());
                }

            }
        }
    }
}
