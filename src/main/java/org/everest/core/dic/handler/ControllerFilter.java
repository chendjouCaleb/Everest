package org.everest.core.dic.handler;

public class ControllerFilter implements ITypeFilter {


    public boolean isAdmissible(Class type) {
        return
                type.getName().endsWith("Controller") &&
                        !type.getSimpleName().equals("HttpController") &&
                        !type.getSimpleName().equals("Controller");
    }
}
