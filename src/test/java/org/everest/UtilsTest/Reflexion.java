package org.everest.UtilsTest;

import org.everest.utils.ReflexionUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Reflexion {

    @Test
    public void testScanPackage() throws IOException, ClassNotFoundException {
        Class[] classes = ReflexionUtils.getClasses("org.everest");

        for (Class clazz: classes){
            System.out.println(clazz.getName());
        }
    }
}
