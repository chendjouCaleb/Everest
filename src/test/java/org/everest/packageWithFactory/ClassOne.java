package org.everest.packageWithFactory;

import org.everest.core.dic.decorator.Resolve;

public class ClassOne {
    @Resolve
    private ClassSix classSix;
    @Resolve private IClass classSeven;

    public ClassSix getClassSix() {
        return classSix;
    }

    public IClass getClassSeven() {
        return classSeven;
    }
}
