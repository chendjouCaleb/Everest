package org.everest.utils;

public class ArrayUtils {
    public static Object findFirstDuplicate(Object[] array){
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length; j++){
                if(i != j && array[i].equals(array[j])){
                    return array[j];
                }
            }
        }
        return null;
    }
}
