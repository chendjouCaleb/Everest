package org.everest.UtilsTest;

import org.everest.utils.ReflexionUtils;
import org.junit.jupiter.api.Test;


import java.io.IOException;

public class Reflexion {

    @Test
    public void testScanPackage() throws IOException, ClassNotFoundException {
        Class[] classes = ReflexionUtils.getClasses("org.everest");

        for (Class clazz: classes){
            System.out.println(clazz.getName());
        }
    }
}
