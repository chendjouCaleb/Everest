package org.everest.packageWithFactory;

import org.everest.decorator.Component;

@Component
public class ClassSeven implements IClass{
    @Override
    public void print() {
        System.out.println("SEVEN CLASS");
    }
}
