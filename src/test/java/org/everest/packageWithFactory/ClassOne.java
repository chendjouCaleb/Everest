package org.everest.packageWithFactory;

import dic.AutoInject;
import org.everest.core.dic.decorator.AutoWired;

public class ClassOne {
    @AutoInject private ClassSix classSix;
    @AutoInject private IClass classSeven;

    public ClassSix getClassSix() {
        return classSix;
    }

    public IClass getClassSeven() {
        return classSeven;
    }
}
